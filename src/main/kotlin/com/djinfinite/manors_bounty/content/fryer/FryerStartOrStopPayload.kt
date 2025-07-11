package com.djinfinite.manors_bounty.content.fryer

import com.djinfinite.manors_bounty.res
import io.netty.buffer.ByteBuf
import net.minecraft.core.BlockPos
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext

data class FryerStartOrStopPayload(val blockPos: BlockPos, val startOrStop: Boolean) : CustomPacketPayload {

    companion object {
        val TYPE: CustomPacketPayload.Type<FryerStartOrStopPayload> =
            CustomPacketPayload.Type(res("fryer_start_or_stop"))
        val STREAM_CODEC: StreamCodec<ByteBuf, FryerStartOrStopPayload> = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            FryerStartOrStopPayload::blockPos,
            ByteBufCodecs.BOOL,
            FryerStartOrStopPayload::startOrStop,
            ::FryerStartOrStopPayload
        )

        fun handleOnServer(payload: FryerStartOrStopPayload, context: IPayloadContext) {
            context.enqueueWork {
                val blockEntity = context.player().level().getBlockEntity(payload.blockPos)
                if (blockEntity is FryerBlockEntity) {
                    if (payload.startOrStop) blockEntity.start() else blockEntity.stop()
                }
            }
        }
    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

}