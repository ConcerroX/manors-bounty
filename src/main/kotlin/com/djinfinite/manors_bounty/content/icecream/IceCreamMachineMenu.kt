package com.djinfinite.manors_bounty.content.icecream

import com.djinfinite.manors_bounty.item.SimpleSlotItemHandler
import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModMenuTypes
import com.djinfinite.manors_bounty.registry.ModTags
import com.djinfinite.manors_bounty.util.addPlayerInventorySlots
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.capabilities.Capabilities

class IceCreamMachineMenu(
    containerId: Int, inventory: Inventory, val blockEntity: IceCreamMachineBlockEntity
) : AbstractContainerMenu(ModMenuTypes.ICE_CREAM_MACHINE.get(), containerId) {

    constructor(
        containerId: Int, inventory: Inventory, extraData: FriendlyByteBuf
    ) : this(
        containerId,
        inventory,
        inventory.player.level().getBlockEntity(extraData.readBlockPos()) as IceCreamMachineBlockEntity
    )

    init {
        addPlayerInventorySlots(inventory, 8, 145)
        addSlot(
            SimpleSlotItemHandler(
                blockEntity.itemHandler, IceCreamMachineBlockEntity.SLOT_ITEM_IN_OUT_CONE, 38, 118
            ).mayPlace {
                it.`is`(ModTags.Items.ICE_CREAM_AND_CONE)
            })
        addSlot(
            SimpleSlotItemHandler(
                blockEntity.itemHandler, IceCreamMachineBlockEntity.SLOT_ITEM_IN_ICE_CREAM, 141, 41
            ).mayPlace {
                // Use the item fluid handler but not ModTags.Items.ICE_CREAM so that we can use varied tanks to insert fluid
                it.getCapability(Capabilities.FluidHandler.ITEM) != null
            })
        addSlot(
            SimpleSlotItemHandler(
                blockEntity.itemHandler, IceCreamMachineBlockEntity.SLOT_ITEM_IN_FRUIT_1, 104, 118
            ).mayPlace {
                it.`is`(ModTags.Items.ICE_CREAM_TYPE)
            })
        addSlot(
            SimpleSlotItemHandler(
            blockEntity.itemHandler, IceCreamMachineBlockEntity.SLOT_ITEM_IN_FRUIT_2, 143, 118
        ).mayPlace {
            blockEntity.isTwoScoops && it.`is`(ModTags.Items.ICE_CREAM_TYPE)
        }.mayPickup {
            blockEntity.isTwoScoops
        })
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack {
        return ItemStack.EMPTY
    }

    override fun stillValid(player: Player): Boolean {
        return stillValid(
            ContainerLevelAccess.create(player.level(), blockEntity.blockPos), player, ModBlocks.ICE_CREAM_MACHINE.get()
        )
    }

}