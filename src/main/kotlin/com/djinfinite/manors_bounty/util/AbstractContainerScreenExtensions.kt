package com.djinfinite.manors_bounty.util

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferBuilder
import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.Rect2i
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.world.inventory.InventoryMenu
import net.minecraft.world.level.material.Fluids
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.IFluidTank
import org.lwjgl.opengl.GL11
import java.util.function.Consumer
import kotlin.math.min

//fun AbstractContainerScreen<*>.renderBackground(guiGraphics: GuiGraphics, texture: ResourceLocation) {
//    RenderSystem.setShader(GameRenderer::getPositionTexShader)
//    RenderSystem.setShaderColor(1F, 1F, 1F, 1F)
//    RenderSystem.setShaderTexture(0, texture)
//    guiGraphics.blit(texture, leftPos, topPos, 0, 0, imageWidth, imageHeight)
//}

fun AbstractContainerScreen<*>.drawFluid(
    guiGraphics: GuiGraphics, bounds: Rect2i, fluidStack: FluidStack, tank: IFluidTank
) {
    if (fluidStack.fluid === Fluids.EMPTY) return
    val fluid = fluidStack.fluid
    val renderProps = IClientFluidTypeExtensions.of(fluid)
    val textureId = renderProps.getStillTexture(fluidStack)
    val texture = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(textureId)

    var scaledAmount = fluidStack.amount * bounds.height / tank.capacity
    if (fluidStack.amount > 0 && scaledAmount < 1) scaledAmount = 1
    scaledAmount = min(scaledAmount, bounds.height)

    RenderSystem.setShader(GameRenderer::getPositionTexColorShader)
    RenderSystem.setShaderColor(1F, 1F, 1F, 1F)
    RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS)
    RenderSystem.enableBlend()
    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)

    val xTileCount = bounds.width / 16
    val xRemainder = bounds.width - xTileCount * 16
    val yTileCount = scaledAmount / 16
    val yRemainder = scaledAmount - yTileCount * 16

    var yStart = bounds.y + bounds.height
    if (fluid.fluidType.density < 0) yStart -= (bounds.height - scaledAmount)
    val color = decomposeColor(renderProps.getTintColor(fluidStack))

    for (xTile in 0..xTileCount) {
        for (yTile in 0..yTileCount) {
            val w = if (xTile == xTileCount) xRemainder else 16
            val h = if (yTile == yTileCount) yRemainder else 16
            val x = bounds.x + xTile * 16F
            val y = yStart - (yTile + 1) * 16F
            if (bounds.width > 0 && h > 0) {
                val maskTop = 16 - h
                val maskRight = 16 - w
                drawFluidTexture(guiGraphics, x, y, texture, maskTop, maskRight, 100F, color)
            }
        }
    }
    RenderSystem.disableBlend()
}

private fun decomposeColor(color: Int): IntArray {
    val res = IntArray(4)
    res[0] = color shr 24 and 0xff
    res[1] = color shr 16 and 0xff
    res[2] = color shr 8 and 0xff
    res[3] = color and 0xff
    return res
}

private fun drawFluidTexture(
    graphics: GuiGraphics,
    xCoord: Float,
    yCoord: Float,
    textureSprite: TextureAtlasSprite,
    maskTop: Int,
    maskRight: Int,
    zLevel: Float,
    cols: IntArray
) {
    val uMin = textureSprite.u0
    val vMin = textureSprite.v0
    val uMax0 = textureSprite.u1
    val vMax0 = textureSprite.v1
    val uMax = uMax0 - maskRight / 16F * (uMax0 - uMin)
    val vMax = vMax0 - maskTop / 16F * (vMax0 - vMin)
    val posMat = graphics.pose().last().pose()
    drawWithTesselator { builder ->
        builder.addVertex(posMat, xCoord, yCoord + 16, zLevel).setUv(uMin, vMax)
            .setColor(cols[1], cols[2], cols[3], cols[0])
        builder.addVertex(posMat, xCoord + 16 - maskRight, yCoord + 16, zLevel).setUv(uMax, vMax)
            .setColor(cols[1], cols[2], cols[3], cols[0])
        builder.addVertex(posMat, xCoord + 16 - maskRight, yCoord + maskTop, zLevel).setUv(uMax, vMin)
            .setColor(cols[1], cols[2], cols[3], cols[0])
        builder.addVertex(posMat, xCoord, yCoord + maskTop, zLevel).setUv(uMin, vMin)
            .setColor(cols[1], cols[2], cols[3], cols[0])
    }
}

private fun drawWithTesselator(consumer: Consumer<BufferBuilder>) {
    val builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR)
    consumer.accept(builder)
    BufferUploader.drawWithShader(builder.buildOrThrow())
}