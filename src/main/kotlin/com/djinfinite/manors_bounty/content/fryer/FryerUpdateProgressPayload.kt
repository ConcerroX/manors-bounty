package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.res
import io.netty.buffer.ByteBuf
import net.minecraft.core.BlockPos
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext

data class FryerUpdateProgressPayload(val blockPos: BlockPos, val workingProgress: Int) : CustomPacketPayload {

    companion object {
        val TYPE: CustomPacketPayload.Type<FryerUpdateProgressPayload> =
            CustomPacketPayload.Type(res("fryer_update_progress"))
        val STREAM_CODEC: StreamCodec<ByteBuf, FryerUpdateProgressPayload> = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            FryerUpdateProgressPayload::blockPos,
            ByteBufCodecs.INT,
            FryerUpdateProgressPayload::workingProgress,
            ::FryerUpdateProgressPayload
        )

        fun handleOnClient(payload: FryerUpdateProgressPayload, context: IPayloadContext) {
            context.enqueueWork {
                val blockEntity = context.player().level().getBlockEntity(payload.blockPos)
                if (blockEntity is FryerBlockEntity) blockEntity.updateWorkingProgress(payload.workingProgress)
            }
        }
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

}