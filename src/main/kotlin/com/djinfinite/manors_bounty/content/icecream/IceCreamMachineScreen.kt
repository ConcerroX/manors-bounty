package com.djinfinite.manors_bounty.content.icecream

import com.djinfinite.manors_bounty.res
import com.djinfinite.manors_bounty.util.drawFluid
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Checkbox
import net.minecraft.client.gui.components.ImageButton
import net.minecraft.client.gui.components.WidgetSprites
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.Rect2i
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.neoforged.neoforge.network.PacketDistributor


class IceCreamMachineScreen(
    menu: IceCreamMachineMenu, playerInventory: Inventory, title: Component
) : AbstractContainerScreen<IceCreamMachineMenu>(menu, playerInventory, title) {

    companion object {
        private val BACKGROUND_TEXTURE = res("textures/gui/ice_cream_machine/background.png")

        // In path /assets/textures/gui/sprites, without extensions
        private val BUTTON_SPRITE = WidgetSprites(
            res("ice_cream_machine/button"),
            res("ice_cream_machine/button_hovered"),
        )
        private var FLUID_BOUNDS = Rect2i(99, 14, 34, 47)
    }

    private val checkbox: Checkbox = Checkbox.builder(
        Component.translatable("gui.manors_bounty.ice_cream_machine_gui.IceCreamTypeCheck"),
        Minecraft.getInstance().font
    ).onValueChange { checkbox, isChecked ->
        PacketDistributor.sendToServer(IceCreamMachinePayload(menu.blockEntity.blockPos, checkbox.selected()))
        menu.blockEntity.isTwoScoops = isChecked
    }.selected(menu.blockEntity.isTwoScoops).build()

    init {
        imageWidth = 176
        imageHeight = 227
    }

    override fun init() {
        super.init()
        addRenderableWidget(ImageButton(26 + leftPos, 3 + topPos, 40, 34, BUTTON_SPRITE) {
            PacketDistributor.sendToServer(IceCreamMachinePayload(menu.blockEntity.blockPos, checkbox.selected()))
            menu.blockEntity.tryCraftItem()
        })
        addRenderableWidget(checkbox.apply {
            x = leftPos + 140
            y = topPos + 17
        })
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        super.render(guiGraphics, mouseX, mouseY, partialTick)
        renderFluids(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        guiGraphics.blit(BACKGROUND_TEXTURE, leftPos, topPos, 0F, 0F, imageWidth, imageHeight, imageWidth, imageHeight)
    }

    override fun renderLabels(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {}

    private fun renderFluids(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        val fluidHandler = menu.blockEntity.fluidHandler
        val y = FLUID_BOUNDS.y + topPos
//        for (i in 0 until fluidHandler.tanks) {
//            val fluidStack = fluidHandler.getFluidInTank(i)
//            if (!fluidStack.isEmpty) {
//                val h = FLUID_BOUNDS.height * fluidStack.amount / fluidHandler.capacity
//                y -= h
        val bounds = Rect2i(FLUID_BOUNDS.x + leftPos, y, FLUID_BOUNDS.width, FLUID_BOUNDS.height)
//                println(FLUID_BOUNDS.height.toString() + " " + y + " " + (FLUID_BOUNDS.y + topPos + FLUID_BOUNDS.height))
        drawFluid(guiGraphics, bounds, fluidHandler.getFluidInTank(0), fluidHandler)
//            }
//        }
    }

}