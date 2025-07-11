package com.djinfinite.manors_bounty.content.icecream

import com.djinfinite.manors_bounty.registry.ModBlockEntityTypes
import com.djinfinite.manors_bounty.registry.ModRecipeTypes
import com.djinfinite.manors_bounty.util.isEmpty
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientGamePacketListener
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.capabilities.Capabilities
import net.neoforged.neoforge.fluids.FluidUtil
import net.neoforged.neoforge.fluids.capability.IFluidHandler
import net.neoforged.neoforge.fluids.capability.templates.FluidTank
import net.neoforged.neoforge.items.ItemStackHandler
import kotlin.jvm.optionals.getOrNull


class IceCreamMachineBlockEntity(pos: BlockPos, blockState: BlockState) :
    BlockEntity(ModBlockEntityTypes.ICE_CREAM_MACHINE.get(), pos, blockState), MenuProvider {

    companion object {
        private const val CONTAINER_SIZE = 4
        const val SLOT_ITEM_IN_OUT_CONE = 0
        const val SLOT_ITEM_IN_ICE_CREAM = 1
        const val SLOT_ITEM_IN_FRUIT_1 = 2
        const val SLOT_ITEM_IN_FRUIT_2 = 3
    }

    var isTwoScoops = false
    val itemHandler = object : ItemStackHandler(CONTAINER_SIZE) {
        override fun onContentsChanged(slot: Int) {
            val itemStack = getStackInSlot(slot)
            val itemFluidHandler = itemStack.getCapability(Capabilities.FluidHandler.ITEM)
            if (slot == SLOT_ITEM_IN_ICE_CREAM && itemFluidHandler != null) {
                val actionResult = if (itemFluidHandler.isEmpty) {
                    FluidUtil.tryFillContainerAndStow(itemStack, fluidHandler, this, Int.MAX_VALUE, null, true)
                } else {
                    FluidUtil.tryEmptyContainerAndStow(itemStack, fluidHandler, this, Int.MAX_VALUE, null, true)
                }
                if (actionResult.isSuccess) stacks[slot] = actionResult.result
            }
            setChanged()
            level?.sendBlockUpdated(blockPos, getBlockState(), getBlockState(), 3)
        }
    }

    val fluidHandler = object : FluidTank(4000) {
        override fun onContentsChanged() {
            setChanged()
            level?.sendBlockUpdated(blockPos, getBlockState(), getBlockState(), 3)
        }
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.loadAdditional(tag, registries)
        itemHandler.deserializeNBT(registries, tag.getCompound("itemHandler"))
        fluidHandler.readFromNBT(registries, tag)
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        tag.put("itemHandler", itemHandler.serializeNBT(registries))
        fluidHandler.writeToNBT(registries, tag)
        super.saveAdditional(tag, registries)
    }

    override fun createMenu(containerId: Int, playerInventory: Inventory, player: Player): AbstractContainerMenu {
        return IceCreamMachineMenu(containerId, playerInventory, this)
    }

    override fun getDisplayName(): Component {
        return Component.translatable("block.manors_bounty.ice_cream_machine")
    }

    override fun getUpdateTag(pRegistries: HolderLookup.Provider): CompoundTag {
        return saveWithoutMetadata(pRegistries)
    }

    override fun getUpdatePacket(): Packet<ClientGamePacketListener> {
        return ClientboundBlockEntityDataPacket.create(this)
    }

    private fun getCurrentRecipe(): RecipeHolder<IceCreamMachineRecipe>? {
        return level?.let {
            level?.recipeManager?.getRecipeFor(
                ModRecipeTypes.ICE_CREAM_MACHINE.get(), IceCreamMachineRecipe.Input(itemHandler, fluidHandler), it
            )?.getOrNull()
        }
    }

    fun tryCraftItem() {
        val recipe = getCurrentRecipe() ?: return
        val output = recipe.value.result
        itemHandler.extractItem(SLOT_ITEM_IN_OUT_CONE, 1, false)
        fluidHandler.drain(recipe.value.fluidIngredient.amount(), IFluidHandler.FluidAction.EXECUTE)
        itemHandler.extractItem(SLOT_ITEM_IN_FRUIT_1, 1, false)
        itemHandler.setStackInSlot(SLOT_ITEM_IN_OUT_CONE, output)
    }

    fun dropContents() {
        level?.let {
            val inventory = SimpleContainer(itemHandler.slots)
            for (i in 0 until itemHandler.slots) {
                inventory.setItem(i, itemHandler.getStackInSlot(i))
            }
            Containers.dropContents(it, worldPosition, inventory)
        }
    }

//    fun <T> getCapability(capability: Capability<T>, facing: Direction?): LazyOptional<T> {
//        return if (!this.remove && facing != null && capability === ForgeCapabilities.ITEM_HANDLER) this.handlers.get(
//            facing.ordinal).cast() else super.getCapability(capability, facing)
//    }

//    override fun setRemoved() {
//        super.setRemoved()
//        for (handler in this.handlers) {
//            handler.invalidate()
//        }
//    }

}