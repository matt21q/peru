package net.matt.perumod.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;



public class AltitudeSickness  extends MobEffect {

    public AltitudeSickness(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor); // Color gris para el efecto
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        // Ralentiza el movimiento del jugador
        pLivingEntity.setSpeed(pLivingEntity.getSpeed() - 0.1f * (pAmplifier + 1));
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        int i = 40 >> amplifier;
        return i > 0 ? duration % i == 0 : true;
    }



}
