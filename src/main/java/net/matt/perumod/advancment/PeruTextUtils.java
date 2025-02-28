package net.matt.perumod.advancment;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class PeruTextUtils {
    public PeruTextUtils() {
    }
    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable("perusdelight." + key, args);
    }
}
