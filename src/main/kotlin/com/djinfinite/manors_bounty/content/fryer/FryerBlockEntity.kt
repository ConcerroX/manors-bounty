package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.registry.ModBlockEntityTypes
import com.djinfinite.manors_bounty.registry.ModRecipeTypes
import com.djinfinite.manors_bounty.util.inWholeTicks
import com.djinfinite.manors_bounty.util.isEmpty
import com.djinfinite.manors_bounty.util.letChanced
import com.mojang.math.Axis
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.fluids.FluidUtil
import net.neoforged.neoforge.fluids.capability.IFluidHandler
import net.neoforged.neoforge.fluids.capability.templates.FluidTank
import net.neoforged.neoforge.items.ItemStackHandler
import org.joml.Quaternionf
import kotlin.time.Duration.Companion.seconds


class FryerBlockEntity(pos: BlockPos, blockState: BlockState) :
    BlockEntity(ModBlockEntityTypes.FRYER.get(), pos, blockState), MenuProvider {

    companion object {
        private const val ITEM_HANDLER_KEY = "Items"
        private const val IS_WORKING_KEY = "IsWorking"
        private const val WORKING_PROGRESS_KEY = "WorkingProgress"
        const val SLOT_OIL = 0
        const val SLOT_INGREDIENT_1 = 1
        const val SLOT_INGREDIENT_2 = 2
        const val SLOT_INGREDIENT_3 = 3
        const val SLOT_INGREDIENT_4 = 4
        val SLOT_INGREDIENT_RANGE = 0..4
    }

    val itemHandler = object : ItemStackHandler(5) {
        override fun getSlotLimit(slot: Int) = if (slot == SLOT_OIL) 1 else 4
        override fun onContentsChanged(slot: Int) {
            val stack = getStackInSlot(slot)
            val itemFluidHandler = stack.getCapability(Capabilities.FluidHandler.ITEM)
            if (slot == SLOT_OIL && itemFluidHandler != null) {
                val actionResult = if (itemFluidHandler.isEmpty) {
                    FluidUtil.tryFillContainerAndStow(stack, fluidTank, this, Int.MAX_VALUE, null, true)
                } else {
                    FluidUtil.tryEmptyContainerAndStow(stack, fluidTank, this, Int.MAX_VALUE, null, true)
                }
                if (actionResult.isSuccess) stacks[slot] = actionResult.result
            }
            setChanged()
            level?.sendBlockUpdated(blockPos, getBlockState(), getBlockState(), 1 or 2)
        }
    }
    val fluidTank = object : FluidTank(4000) {
        override fun onContentsChanged() {
            val newState = blockState.setValue(FryerBlock.HAS_OIL, !isEmpty)
            level?.setBlockAndUpdate(pos, newState)
        }
    }

    var isWorking = false
    var workingProgress = 0

    override fun getUpdatePacket(): ClientboundBlockEntityDataPacket = ClientboundBlockEntityDataPacket.create(this)
    override fun getUpdateTag(registries: HolderLookup.Provider): CompoundTag = saveWithoutMetadata(registries)
    override fun getDisplayName(): MutableComponent = Component.empty()
    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player) =
        FryerMenu(containerId, playerInventory, this)

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        fluidTank.readFromNBT(registries, tag)
        itemHandler.deserializeNBT(registries, tag.getCompound(ITEM_HANDLER_KEY))
        isWorking = tag.getBoolean(IS_WORKING_KEY)
        workingProgress = tag.getInt(WORKING_PROGRESS_KEY)
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        fluidTank.writeToNBT(registries, tag)
        tag.put(ITEM_HANDLER_KEY, itemHandler.serializeNBT(registries))
        tag.putBoolean(IS_WORKING_KEY, isWorking)
        tag.putInt(WORKING_PROGRESS_KEY, workingProgress)
    }

    internal fun dropContents() {
        level?.let {
            val container = SimpleContainer(itemHandler.slots)
            for (i in 0 until itemHandler.slots) container.setItem(i, itemHandler.getStackInSlot(i))
            Containers.dropContents(it, worldPosition, container)
        }
    }

    internal fun canStart(): Boolean {
        return fluidTank.fluidAmount >= 250
    }

    internal fun start() {
        isWorking = true
        workingProgress = 0
        letChanced(getOilConsumingChance()) {
            fluidTank.drain(250, IFluidHandler.FluidAction.EXECUTE)
        }

        if (level == null || level?.isClientSide == true) return
        level?.scheduleTick(worldPosition, blockState.block, 1.seconds.inWholeTicks)
    }

    internal fun stop() {
        isWorking = false
    }

    internal fun getOilConsumingChance(): Double {
        var count = 0
        // Without the bucket slot 0
        for (i in 1..SLOT_INGREDIENT_RANGE.last) count += itemHandler.getStackInSlot(i).count
        return (count * 0.0625).coerceAtMost(1.0)
    }

    internal fun updateWorkingProgress(workingProgress: Int) {
        this.workingProgress = workingProgress
        if (workingProgress == 4) {
            for (i in SLOT_INGREDIENT_RANGE) tryProcessRecipeForSlot(i, false)
        } else if (workingProgress == 8) {
            for (i in SLOT_INGREDIENT_RANGE) tryProcessRecipeForSlot(i, true)
        } else if (workingProgress == 12) {
            for (i in SLOT_INGREDIENT_RANGE) itemHandler.setStackInSlot(
                i, ItemStack(Items.CHARCOAL, itemHandler.getStackInSlot(i).count)
            )
        }
    }

    private fun tryProcessRecipeForSlot(slot: Int, isLongTime: Boolean) {
        level?.let { level ->
            val recipeManager = level.recipeManager
            val stack = itemHandler.getStackInSlot(slot)
            recipeManager.getRecipeFor(
                ModRecipeTypes.FRYER.get(), FryerRecipe.Input(stack, isLongTime), level
            ).ifPresentOrElse({
                val result = it.value.result
                itemHandler.setStackInSlot(slot, ItemStack(result.item, result.count * stack.count))
            }, {
                val longTimeRecipeOrNull =
                    recipeManager.getRecipeFor(ModRecipeTypes.FRYER.get(), FryerRecipe.Input(stack, true), level)
                println("$isLongTime $longTimeRecipeOrNull $stack")
                if (!isLongTime && longTimeRecipeOrNull.isEmpty) itemHandler.setStackInSlot(
                    slot, ItemStack(Items.CHARCOAL, stack.count)
                )
            })
        }
    }

}