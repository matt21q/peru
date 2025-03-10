package net.matt.perumod;

import com.mojang.logging.LogUtils;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.block.entity.ModBlockEntityTypes;
import net.matt.perumod.effect.ModEffects;
import net.matt.perumod.entity.ModEntities;
import net.matt.perumod.entity.client.*;
import net.matt.perumod.entity.client.baby.BBCuyAndinoRenderer;
import net.matt.perumod.entity.client.baby.BBCuyIntiRenderer;
import net.matt.perumod.entity.client.baby.BBCuyPeruRenderer;
import net.matt.perumod.item.ModCreativeModTabs;
import net.matt.perumod.item.ModItems;
import net.matt.perumod.sound.ModSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
@Mod(PeruMod.MOD_ID)
public class PeruMod
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "perusdelight");

    public static final String MOD_ID = "perusdelight";
    public static final Logger LOGGER = LogUtils.getLogger();
    public PeruMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.MOB_EFFECTS.register(modEventBus);
        ModBlockEntityTypes.TILES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModSounds.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.CUY_INTI.get(), CuyIntiRenderer::new);
            EntityRenderers.register(ModEntities.CUY_ANDINO.get(), CuyAndinoRenderer::new);
            EntityRenderers.register(ModEntities.CUY_PERUANO.get(), CuyPeruRenderer::new);
            EntityRenderers.register(ModEntities.BB_CUY_PERUANO.get(), BBCuyPeruRenderer::new);
            EntityRenderers.register(ModEntities.BB_CUY_INTI.get(), BBCuyIntiRenderer::new);
            EntityRenderers.register(ModEntities.BB_CUY_ANDINO.get(), BBCuyAndinoRenderer::new);
        }
    }
}
