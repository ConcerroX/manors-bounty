package com.djinfinite.manors_bounty.content.fryer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.LightTexture
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.LightLayer
import org.joml.Quaternionf

class FryerBlockEntityRenderer(@Suppress("UNUSED_PARAMETER") context: BlockEntityRendererProvider.Context) :
    BlockEntityRenderer<FryerBlockEntity> {

    private fun getLightLevel(level: Level, pos: BlockPos) =
        LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos), level.getBrightness(LightLayer.SKY, pos))

    override fun render(
        blockEntity: FryerBlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        poseStack.pushPose()

        poseStack.translate(0.5, 0.0, 0.5)
        val dir = blockEntity.blockState.getValue(FryerBlock.FACING)
        var angle = dir.toYRot()
        // It's rotated inversely for WEST/EAST directions!?
        if (dir.axis == Direction.Axis.X) angle = 360 - angle
        poseStack.mulPose(Axis.YP.rotationDegrees(angle))
        poseStack.translate(-0.5, 0.0, -0.5)

        // Top Left
        poseStack.translate(6.125 / 16.0, 12.0 / 16.0, 5.5 / 16.0)
        renderItemSlot(blockEntity, 1, poseStack, bufferSource)
        // Top Right
        poseStack.translate(3.75 / 16.0, 0.0, 0.0)
        renderItemSlot(blockEntity, 2, poseStack, bufferSource)
        // Bottom Right
        poseStack.translate(0.0, 0.0, 4.0 / 16.0)
        renderItemSlot(blockEntity, 3, poseStack, bufferSource)
        // Bottom Left
        poseStack.translate(-3.75 / 16.0, 0.0, 0.0)
        renderItemSlot(blockEntity, 4, poseStack, bufferSource)

        poseStack.popPose()
    }

    private fun renderItemSlot(
        blockEntity: FryerBlockEntity, slot: Int, poseStack: PoseStack, bufferSource: MultiBufferSource
    ) {
        poseStack.pushPose()
        poseStack.mulPose(Quaternionf().rotateX(Mth.DEG_TO_RAD * -75F))
        poseStack.scale(0.25F, 0.25F, 0.25F)
        val itemRenderer = Minecraft.getInstance().itemRenderer
        val stack = blockEntity.itemHandler.getStackInSlot(slot)
        val level = blockEntity.level ?: return
        itemRenderer.renderStatic(
            stack,
            ItemDisplayContext.FIXED,
            getLightLevel(level, blockEntity.blockPos),
            OverlayTexture.NO_OVERLAY,
            poseStack,
            bufferSource,
            level,
            1
        )
        poseStack.popPose()
    }

}