package net.matt.perumod.util;

import net.matt.perumod.PeruMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> WILD_CROPS = modBlockTag("wild_crops");

    public static class Blocks {
        //public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PeruMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FISH_CEVICHE = tag("fish_ceviche");
        public static final TagKey<Item> LOMO_SALTADO_MEAT = tag("lomo_saltado_meat");
        public static final TagKey<Item> MORTAR_FUEL = tag("mortar_fuel");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(PeruMod.MOD_ID, name));
        }
    }
    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(new ResourceLocation(PeruMod.MOD_ID, path));
    }
}
