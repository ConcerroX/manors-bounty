package com.djinfinite.manors_bounty.content.icecream

import com.djinfinite.manors_bounty.res
import io.netty.buffer.ByteBuf
import net.minecraft.core.BlockPos
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.neoforged.neoforge.network.handling.IPayloadContext


data class IceCreamMachinePayload(val blockPos: BlockPos, val isTwoScoops: Boolean) : CustomPacketPayload {

    companion object {
        val TYPE: CustomPacketPayload.Type<IceCreamMachinePayload> = CustomPacketPayload.Type(res("ice_cream_machine"))
        val STREAM_CODEC: StreamCodec<ByteBuf, IceCreamMachinePayload> = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            IceCreamMachinePayload::blockPos,
            ByteBufCodecs.BOOL,
            IceCreamMachinePayload::isTwoScoops,
            ::IceCreamMachinePayload
        )

        fun handleOnServer(payload: IceCreamMachinePayload, context: IPayloadContext) {
            context.enqueueWork {
                val blockEntity = context.player().level().getBlockEntity(payload.blockPos)
                if (blockEntity is IceCreamMachineBlockEntity) {
                    blockEntity.isTwoScoops = payload.isTwoScoops
                    blockEntity.tryCraftItem()
                }
            }
        }

    }

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = TYPE

}