package com.djinfinite.manors_bounty.item

import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.items.IItemHandler
import net.neoforged.neoforge.items.SlotItemHandler

class SimpleSlotItemHandler(
    itemHandler: IItemHandler,
    index: Int,
    xPosition: Int,
    yPosition: Int,
) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {

    private var mayPlaceCallback: (ItemStack) -> Boolean = { true }
    private var mayPickupCallback: (Player) -> Boolean = { true }

    override fun mayPlace(stack: ItemStack) = mayPlaceCallback(stack)
    override fun mayPickup(player: Player) = mayPickupCallback(player)

    fun mayPlace(itemValidator: (ItemStack) -> Boolean): SimpleSlotItemHandler {
        this.mayPlaceCallback = itemValidator
        return this
    }

    fun mayPickup(mayPickupCallback: (Player) -> Boolean): SimpleSlotItemHandler {
        this.mayPickupCallback = mayPickupCallback
        return this
    }

}