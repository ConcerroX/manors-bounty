package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.item.SimpleSlotItemHandler
import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModMenuTypes
import com.djinfinite.manors_bounty.util.VANILLA_SLOT_RANGE
import com.djinfinite.manors_bounty.util.addPlayerInventorySlots
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.capabilities.Capabilities

class FryerMenu(containerId: Int, inventory: Inventory, val blockEntity: FryerBlockEntity) :
    AbstractContainerMenu(ModMenuTypes.FRYER.get(), containerId) {

    constructor(containerId: Int, inventory: Inventory, extraData: FriendlyByteBuf) : this(
        containerId, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()) as FryerBlockEntity
    )

    private val mayPlace = { _: ItemStack -> !blockEntity.isWorking }
    private val mayPickup = { _: Player -> !blockEntity.isWorking }

    internal val slotIngredient1 =
        SimpleSlotItemHandler(blockEntity.itemHandler, FryerBlockEntity.SLOT_INGREDIENT_1, 42, 22).mayPlace(mayPlace)
            .mayPickup(mayPickup)
    internal val slotIngredient2 =
        SimpleSlotItemHandler(blockEntity.itemHandler, FryerBlockEntity.SLOT_INGREDIENT_2, 76, 22).mayPlace(mayPlace)
            .mayPickup(mayPickup)
    internal val slotIngredient3 =
        SimpleSlotItemHandler(blockEntity.itemHandler, FryerBlockEntity.SLOT_INGREDIENT_3, 42, 56).mayPlace(mayPlace)
            .mayPickup(mayPickup)
    internal val slotIngredient4 =
        SimpleSlotItemHandler(blockEntity.itemHandler, FryerBlockEntity.SLOT_INGREDIENT_4, 76, 56).mayPlace(mayPlace)
            .mayPickup(mayPickup)

    init {
        addPlayerInventorySlots(inventory, 8, 145)
        addSlot(
            SimpleSlotItemHandler(
                blockEntity.itemHandler, FryerBlockEntity.SLOT_OIL, 141, 35
            ).mayPlace { it.getCapability(Capabilities.FluidHandler.ITEM) != null })
        addSlot(slotIngredient1)
        addSlot(slotIngredient2)
        addSlot(slotIngredient3)
        addSlot(slotIngredient4)
    }

    override fun stillValid(player: Player) =
        stillValid(ContainerLevelAccess.create(player.level(), blockEntity.blockPos), player, ModBlocks.FRYER.get())

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        val sourceSlot = slots[index]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY
        val sourceStack = sourceSlot.item

        val vanillaEnd = VANILLA_SLOT_RANGE.last + 1
        val slotRange =
            vanillaEnd + FryerBlockEntity.SLOT_INGREDIENT_RANGE.first..vanillaEnd + FryerBlockEntity.SLOT_INGREDIENT_RANGE.last
        if (index in slotRange) {
            // Move to the inventory
            if (!moveItemStackTo(sourceStack, 0, vanillaEnd, false)) {
                return ItemStack.EMPTY
            }
        } else if (!moveItemStackTo(sourceStack, slotRange.first, slotRange.last + 1, false)) {
            return ItemStack.EMPTY
        }

        if (sourceStack.isEmpty) {
            sourceSlot.setByPlayer(ItemStack.EMPTY)
        } else {
            sourceSlot.setChanged()
        }
        return sourceStack.copy()
    }

}