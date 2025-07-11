package com.djinfinite.manors_bounty.util

import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.Slot

private const val SLOT_SIZE = 18
private const val SLOT_INVENTORY_HOT_BAR_GAP = 4

val VANILLA_SLOT_RANGE = 0 until (9 * 4)

fun AbstractContainerMenu.addPlayerInventorySlots(
    inventory: Inventory, x: Int, y: Int
) {
    for (r in 0..2) {
        for (c in 0..8) {
            addSlot(Slot(inventory, c + r * 9 + 9, x + c * SLOT_SIZE, y + r * SLOT_SIZE))
        }
    }
    for (i in 0..8) {
        addSlot(Slot(inventory, i, x + i * SLOT_SIZE, y + SLOT_SIZE * 3 + SLOT_INVENTORY_HOT_BAR_GAP))
    }
}