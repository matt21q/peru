package net.matt.perumod.util;

import net.matt.perumod.PeruMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType LEMON = WoodType.register(new WoodType(PeruMod.MOD_ID + ":lemon", BlockSetType.OAK));
}
