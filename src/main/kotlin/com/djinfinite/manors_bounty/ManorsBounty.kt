package com.djinfinite.manors_bounty

import com.djinfinite.manors_bounty.registry.ModBlockEntityTypes
import com.djinfinite.manors_bounty.registry.ModBlocks
import com.djinfinite.manors_bounty.registry.ModCreativeModeTabs
import com.djinfinite.manors_bounty.registry.ModItems
import com.djinfinite.manors_bounty.registry.ModMenuTypes
import com.djinfinite.manors_bounty.registry.ModMobEffects
import com.djinfinite.manors_bounty.registry.ModRecipeSerializers
import com.djinfinite.manors_bounty.registry.ModRecipeTypes
import com.mojang.logging.LogUtils
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.server.ServerStartingEvent

fun res(path: String): ResourceLocation {
    return ResourceLocation.fromNamespaceAndPath(ManorsBounty.ID, path)
}

@Mod(ManorsBounty.ID)
class ManorsBounty(modEventBus: IEventBus, modContainer: ModContainer) {

    init {
        ModItems.ITEMS.register(modEventBus)
        ModBlocks.BLOCKS.register(modEventBus)
        ModMenuTypes.MENU_TYPES.register(modEventBus)
        ModMobEffects.MOB_EFFECTS.register(modEventBus)
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus)
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus)
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus)
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus)

        NeoForge.EVENT_BUS.register(this)
        modContainer.registerConfig(ModConfig.Type.COMMON, ManorsBountyConfig.SPEC)
    }

    @SubscribeEvent
    fun onServerStarting(event: ServerStartingEvent?) {
        LOGGER.info("HELLO from server starting")
    }

    companion object {
        const val ID = "manors_bounty"
        private val LOGGER = LogUtils.getLogger()
    }

}