package net.matt.perumod.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;


public class Foodvalue {
    public static final FoodProperties SALCHIPAPA = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationMod(0.49f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 1000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0), 1.0F)
            .build();

    public static final FoodProperties CEVICHE = (new FoodProperties.Builder())
            .nutrition(8)
            .saturationMod(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 600, 0), 1.0F)
            .effect(() -> new MobEffectInstance(net.matt.perumod.effect.ModEffects.AMPHIBIAN_EFFECT.get(), 1200, 0), 1.0F)
            .build();

    public static final FoodProperties LOMO_SALTADO = (new FoodProperties.Builder())
            .nutrition(7)
            .saturationMod(0.55f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT.get(), 1000, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1000, 1), 1.0F)
            .build();

    public static final FoodProperties LEMONADE = (new FoodProperties.Builder())
            .alwaysEat().effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0F).build();
}