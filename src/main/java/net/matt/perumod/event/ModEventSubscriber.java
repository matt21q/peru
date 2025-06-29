package net.matt.perumod.event;


import net.matt.perumod.PeruMod;
import net.matt.perumod.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PeruMod.MOD_ID)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;


        if (player.getY() > 170) {
            // Aplica el efecto de mareo si no lo tiene ya
            if (!player.hasEffect(MobEffects.CONFUSION)) {
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200000000, 0, false, false));
            }
        } else {
            // Remueve el efecto de mareo si está por debajo de 170 bloques
            if (player.hasEffect(MobEffects.CONFUSION)) {
                player.removeEffect(MobEffects.CONFUSION);
            }
        }

        // Verifica si el jugador está a más de 140 bloques de altura
        if (player.getY() > 140) {
            // Aplica el efecto de reducción de velocidad si no lo tiene ya
            if (!player.hasEffect(ModEffects.ALTITUDE_SICKNESS.get())) {
                player.addEffect(new MobEffectInstance(ModEffects.ALTITUDE_SICKNESS.get(), 20000000, 0, true, true));
            }
        } else {
            // Remueve el efecto de reducción de velocidad si está por debajo de 140 bloques
            if (player.hasEffect(ModEffects.ALTITUDE_SICKNESS.get())) {
                player.removeEffect(ModEffects.ALTITUDE_SICKNESS.get());
            }
        }
    }
}