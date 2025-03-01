package net.matt.perumod.effect;

import net.matt.perumod.PeruMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PeruMod.MOD_ID);

    public static final RegistryObject<MobEffect> AMPHIBIAN_EFFECT = MOB_EFFECTS.register("amphibian",
            () -> new AmphibianEffect(MobEffectCategory.BENEFICIAL, 0x1fb192)); // Color verde


    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
