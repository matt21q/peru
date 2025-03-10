package net.matt.perumod.item;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.entity.ModEntities;
import net.matt.perumod.sound.ModSounds;
import net.matt.perumod.util.Foodvalue;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import static vectorwing.farmersdelight.common.registry.ModItems.basicItem;
import static vectorwing.farmersdelight.common.registry.ModItems.foodItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PeruMod.MOD_ID);

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",
            () -> new Item(foodItem(FoodValues.TOMATO)));

    public static final RegistryObject<Item> LEMON_SLICE = ITEMS.register("lemon_slice",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FRENCH_FRIES = ITEMS.register("french_fries",
            () -> new Item(foodItem(FoodValues.FRIED_EGG)));

    public static final RegistryObject<Item> SAUSAGE = ITEMS.register("sausage",
            () -> new Item(foodItem(FoodValues.BACON)));

    public static final RegistryObject<Item> COOKED_SAUSAGE = ITEMS.register("cooked_sausage",
            () -> new Item(foodItem(FoodValues.COOKED_BACON)));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(foodItem(FoodValues.ONION)));

    public static final RegistryObject<Item> SALCHIPAPA = ITEMS.register("salchipapa",
            () -> new Item(foodItem(Foodvalue.SALCHIPAPA)));

    public static final RegistryObject<Item> CORN_KERNELS = ITEMS.register("corn_kernels",
            () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties()));

    public static final RegistryObject<Item> SWEET_POTATO = ITEMS.register("sweet_potato",
            () -> new Item(foodItem(Foods.POTATO)));

    public static final RegistryObject<Item> BAKED_SWEET_POTATO = ITEMS.register("baked_sweet_potato",
            () -> new Item(foodItem(Foods.BAKED_POTATO)));

    public static final RegistryObject<Item> POISONOUS_SWEET_POTATO = ITEMS.register("poisonous_sweet_potato",
            () -> new Item(foodItem(Foods.POISONOUS_POTATO)));

    public static final RegistryObject<Item> PURPLE_ONION = ITEMS.register("purple_onion",
            () -> new Item(foodItem(FoodValues.ONION)));

    public static final RegistryObject<Item> CEVICHE = ITEMS.register("ceviche",
            () -> new Item(foodItem(Foodvalue.CEVICHE)));

    public static final RegistryObject<Item> CUY_PERUANO_SPANW_EGG = ITEMS.register("cuy_peruano_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CUY_PERUANO, 0xf8f9f9 , 0xf8f9f9 , new Item.Properties()));


    public static final RegistryObject<Item> BB_CUY_PERUANO_SPANW_EGG = ITEMS.register("bb_cuy_peruano_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BB_CUY_PERUANO, 0xa87540, 0xf3f3f3, new Item.Properties()));

    public static final RegistryObject<Item> BB_CUY_INTI_SPANW_EGG = ITEMS.register("bb_cuy_inti_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BB_CUY_INTI, 0x7f5635, 0xf3f3f3, new Item.Properties()));

    public static final RegistryObject<Item> BB_CUY_ANDINO_SPANW_EGG = ITEMS.register("bb_cuy_andino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BB_CUY_ANDINO, 0x90b7c8, 0xf3f3f3, new Item.Properties()));

    public static final RegistryObject<Item> CUY_ANDINO_SPANW_EGG = ITEMS.register("cuy_andino_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CUY_ANDINO, 0xf8f9f9 , 0xf8f9f9 , new Item.Properties()));

    public static final RegistryObject<Item> CUY_INTI_SPANW_EGG = ITEMS.register("cuy_inti_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CUY_INTI, 0xf8f9f9 , 0xf8f9f9 , new Item.Properties()));

    public static final RegistryObject<Item> LEMON_SIGN = ITEMS.register("lemon_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.LEMON_SIGN.get(), ModBlocks.LEMON_WALL_SIGN.get()));
    public static final RegistryObject<Item> LEMON_HANGING_SIGN = ITEMS.register("lemon_hanging_sign",
            () -> new HangingSignItem(ModBlocks.LEMON_HANGING_SIGN.get(), ModBlocks.LEMON_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> THE_CONDOR_PASSES_MUSIC_DISC = ITEMS.register("the_condor_passes_music_disc",
            () -> new RecordItem(6, ModSounds.THE_CONDOR_PASSES, new Item.Properties().stacksTo(1), 3000));

    public static final RegistryObject<Item> PERUVIAN_POTATO = ITEMS.register("peruvian_potato",
            () -> new Item(foodItem(Foods.POTATO)));

    public static final RegistryObject<Item> CANCHAN_POTATO = ITEMS.register("canchan_potato",
            () -> new Item(foodItem(Foods.POTATO)));

    public static final RegistryObject<Item> BLACK_POTATO = ITEMS.register("black_potato",
            () -> new Item(foodItem(Foods.POTATO)));

    public static final RegistryObject<Item> WILD_VARIANTS_POTATOES = ITEMS.register("wild_variants_potatoes",
            () -> new BlockItem(ModBlocks.WILD_VARIANTS_POTATOES.get(), basicItem()));

    public static final RegistryObject<Item> WILD_PURPLE_ONIONS = ITEMS.register("wild_purple_onions",
            () -> new BlockItem(ModBlocks.WILD_PURPLE_ONIONS.get(), basicItem()));

    public static final RegistryObject<Item> FRIED_CUY_BLOCK = ITEMS.register("fried_cuy_block",
            () -> new BlockItem(ModBlocks.FRIED_CUY_BLOCK.get(), basicItem().stacksTo(1)));

    public static final RegistryObject<Item> FRIED_CUY = ITEMS.register("fried_cuy",
            () -> new ConsumableItem(bowlFoodItem(FoodValues.ROAST_CHICKEN), true));

    public static final RegistryObject<Item> MUD_STOVE;
    static {

        MUD_STOVE = ITEMS.register("mud_stove", () -> new BlockItem((Block) ModBlocks.MUD_STOVE.get(), basicItem()));
    }

    public static Item.Properties bowlFoodItem(FoodProperties food) {
        return new Item.Properties().food(food).craftRemainder(Items.BOWL).stacksTo(16);
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
