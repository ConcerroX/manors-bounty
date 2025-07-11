package com.djinfinite.manors_bounty

import com.djinfinite.manors_bounty.registry.*
import com.mojang.logging.LogUtils
import net.minecraft.resources.ResourceLocation
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent

fun res(path: String): ResourceLocation {
    return ResourceLocation.fromNamespaceAndPath(ManorsBounty.ID, path)
}

@Mod(ManorsBounty.ID)
class ManorsBounty(modEventBus: IEventBus, modContainer: ModContainer) {

    init {
        modEventBus.addListener(::onCommonSetup)
        modContainer.registerConfig(ModConfig.Type.COMMON, ManorsBountyConfig.SPEC)

        ModItems.ITEMS.register(modEventBus)
        ModBlocks.BLOCKS.register(modEventBus)
        ModFluids.FLUIDS.register(modEventBus)
        ModFeatures.FEATURES.register(modEventBus)
        ModMenuTypes.MENU_TYPES.register(modEventBus)
        ModFluidTypes.FLUID_TYPES.register(modEventBus)
        ModMobEffects.MOB_EFFECTS.register(modEventBus)
        ModSoundEvents.SOUND_EVENTS.register(modEventBus)
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus)
        ModDataComponents.DATA_COMPONENTS.register(modEventBus)
        ModAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus)
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus)
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus)
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus)

        ModWoodTypes.register()
    }

    fun onCommonSetup(event: FMLCommonSetupEvent) {
        ModItems.FOOD_ITEM_HOLDERS.forEach {
            it.get().foodIngredientType.items.add(it.get())
        }
    }

    companion object {
        const val ID = "manors_bounty"
        private val LOGGER = LogUtils.getLogger()
    }

}