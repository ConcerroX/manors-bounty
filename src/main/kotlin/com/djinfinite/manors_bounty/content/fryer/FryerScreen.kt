package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.mixin.SlotAccessor
import com.djinfinite.manors_bounty.res
import com.djinfinite.manors_bounty.util.drawFluid
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.Rect2i
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.neoforged.neoforge.items.SlotItemHandler
import net.neoforged.neoforge.network.PacketDistributor

class FryerScreen(menu: FryerMenu, playerInventory: Inventory, title: Component) :
    AbstractContainerScreen<FryerMenu>(menu, playerInventory, title) {

    companion object {
        private val BACKGROUND_TEXTURE = res("textures/gui/fryer/background.png")
        private val OIL_TEXTURE = res("textures/gui/fryer/oil.png")
        private val HOB_TEXTURE = res("textures/gui/fryer/hob.png")
        private val HOB_OIL_TEXTURE = res("textures/gui/fryer/hob_oil.png")
        private val SLOTS_TEXTURE = res("textures/gui/fryer/slots.png")
        private val GRADUATION_TEXTURE = res("textures/gui/fryer/graduation.png")
        private val FLUID_BOUNDS = Rect2i(0, 0, 16, 66)
        private val SLOT_BOUNDS = Rect2i(0, 0, 52, 52)
    }

    init {
        imageWidth = 176
        imageHeight = 227
    }

    private val blockEntity = menu.blockEntity
    private val fluidTank = blockEntity.fluidTank

    private fun getKnobTexture(workingProgress: Int) =
        res("textures/gui/fryer/knob_${workingProgress.coerceAtMost(8)}.png")

    private fun getFryingTexture(tick: Int) = res("textures/gui/fryer/frying_${tick.coerceAtMost(3)}.png")

    override fun renderLabels(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {}

    override fun init() {
        super.init()
        layoutSlots()

        val stopComponent = Component.translatable("gui.manors_bounty.fryer.stop")
        addRenderableWidget(object : Button(
            builder(stopComponent) {
                blockEntity.stop()
                layoutSlots()
                PacketDistributor.sendToServer(FryerStartOrStopPayload(blockEntity.blockPos, startOrStop = false))
            }.bounds(leftPos + 116, topPos + 4, 18, 18).tooltip(Tooltip.create(stopComponent))
        ) {
            override fun renderWidget(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
                val texture = getKnobTexture(if (blockEntity.isWorking) blockEntity.workingProgress else 8)
                guiGraphics.blit(texture, x, y, 0F, 0F, width, height, width, height)
            }
        })

        val startComponent = Component.translatable("gui.manors_bounty.fryer.start")
        addRenderableWidget(object : Button(
            builder(startComponent) {
                if (blockEntity.canStart()) {
                    blockEntity.start()
                    layoutSlots()
                    PacketDistributor.sendToServer(
                        FryerStartOrStopPayload(blockEntity.blockPos, startOrStop = true)
                    )
                }
            }.bounds(leftPos + 139, topPos + 4, 18, 18).tooltip(Tooltip.create(startComponent))
        ) {
            override fun renderWidget(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
                guiGraphics.blit(getKnobTexture(8), x, y, 0F, 0F, width, height, width, height)
            }
        })
    }

    override fun render(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        FLUID_BOUNDS.setPosition(leftPos + 141, topPos + 57)
        SLOT_BOUNDS.setPosition(leftPos + 41, topPos + if (blockEntity.isWorking) 51 else 21)
        super.render(guiGraphics, mouseX, mouseY, partialTick)
    }

    override fun renderBg(guiGraphics: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        guiGraphics.blit(BACKGROUND_TEXTURE, leftPos, topPos, 0F, 0F, imageWidth, imageHeight, imageWidth, imageHeight)
        renderFluids(guiGraphics, mouseX, mouseY)

        if (!fluidTank.isEmpty || blockEntity.isWorking) guiGraphics.blit(
            OIL_TEXTURE, leftPos + 23, topPos + 47, 0F, 0F, 88, 78, 88, 78
        )
        if (blockEntity.isWorking) {
            guiGraphics.blit(HOB_OIL_TEXTURE, leftPos + 25, topPos + 35, 0F, 0F, 84, 102, 84, 102)
            // Divide by 2 to slow down the animation
            val tick = blockEntity.level?.gameTime ?: 0
            val fryingTexture = getFryingTexture((tick / 2 % 4).toInt())
            guiGraphics.blit(fryingTexture, leftPos + 51, topPos + 63, 0F, 0F, 32, 32, 32, 32)
            // Add tooltip as player cannot interact with the slots during frying
            if (SLOT_BOUNDS.contains(mouseX, mouseY)) guiGraphics.renderTooltip(
                Minecraft.getInstance().font,
                Component.translatable("gui.manors_bounty.fryer.tip_during_frying"),
                mouseX,
                mouseY
            )
        } else {
            guiGraphics.blit(HOB_TEXTURE, leftPos + 25, topPos + 5, 0F, 0F, 84, 132, 84, 132)
        }
        guiGraphics.blit(
            SLOTS_TEXTURE,
            SLOT_BOUNDS.x,
            SLOT_BOUNDS.y,
            0F,
            0F,
            SLOT_BOUNDS.width,
            SLOT_BOUNDS.height,
            SLOT_BOUNDS.width,
            SLOT_BOUNDS.height
        )
    }

    private fun renderFluids(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int) {
        drawFluid(guiGraphics, FLUID_BOUNDS, fluidTank.getFluidInTank(0), fluidTank)
        if (FLUID_BOUNDS.contains(mouseX, mouseY)) guiGraphics.renderComponentTooltip(
            Minecraft.getInstance().font, listOf(
                Component.translatable("gui.manors_bounty.fryer.oil_amount", fluidTank.fluidAmount),
                Component.translatable(
                    "gui.manors_bounty.fryer.oil_consumption", blockEntity.getOilConsumingChance() * 100
                )
            ), mouseX, mouseY
        )

        val pose = guiGraphics.pose()
        pose.pushPose()
        pose.translate(0.0, 0.0, 2.0)
        guiGraphics.blit(GRADUATION_TEXTURE, FLUID_BOUNDS.x - 1, FLUID_BOUNDS.y + 2, 0F, 0F, 7, 61, 7, 61)
        pose.popPose()
    }

    internal fun layoutSlots() {
        fun moveSlot(slot: SlotItemHandler, x: Int, y: Int) {
            (slot as SlotAccessor).setX(x)
            (slot as SlotAccessor).setY(y)
        }

        val baseX = 42
        val baseY = if (blockEntity.isWorking) 52 else 22
        moveSlot(menu.slotIngredient1, baseX, baseY)
        moveSlot(menu.slotIngredient2, baseX + 34, baseY)
        moveSlot(menu.slotIngredient3, baseX, baseY + 34)
        moveSlot(menu.slotIngredient4, baseX + 34, baseY + 34)
    }

}