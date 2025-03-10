package net.matt.perumod.datagen;

import com.google.common.collect.Sets;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.item.ModItems;
import net.matt.perumod.PeruMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PeruMod.MOD_ID, existingFileHelper);
    }
    public static final String GENERATED = "item/generated";
    @Override
    protected void registerModels() {
        Set<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(i -> PeruMod.MOD_ID.equals(ForgeRegistries.ITEMS.getKey(i).getNamespace()))
                .collect(Collectors.toSet());
        simpleItem(ModItems.SALT);
        saplingItem(ModBlocks.LEMON_SAPLING);
        simpleItem(ModItems.CORN);
        simpleItem(ModItems.FRENCH_FRIES);
        simpleItem(ModItems.SAUSAGE);
        simpleItem(ModItems.COOKED_SAUSAGE);
        simpleItem(ModItems.LEMON);
        simpleItem(ModItems.LEMON_SLICE);
        simpleItem(ModItems.SALCHIPAPA);
        simpleItem(ModItems.CORN_KERNELS);
        simpleItem(ModItems.BAKED_SWEET_POTATO);
        simpleItem(ModItems.POISONOUS_SWEET_POTATO);
        simpleItem(ModItems.SWEET_POTATO);
        simpleItem(ModItems.PURPLE_ONION);
        simpleItem(ModItems.CEVICHE);
        simpleItem(ModItems.CUY_PERUANO_SPANW_EGG);
        simpleItem(ModItems.CUY_ANDINO_SPANW_EGG);
        simpleItem(ModItems.CUY_INTI_SPANW_EGG);
        withExistingParent(ModItems.BB_CUY_PERUANO_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BB_CUY_ANDINO_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.BB_CUY_INTI_SPANW_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        trapdoorItem(ModBlocks.LEMON_TREE_TRAPDOOR);
        simpleItem(ModItems.LEMON_SIGN);
        simpleBlockItem(ModBlocks.LEMON_TREE_DOOR);
        simpleItem(ModItems.LEMON_HANGING_SIGN);
        simpleItem(ModItems.THE_CONDOR_PASSES_MUSIC_DISC);
        simpleItem(ModItems.PERUVIAN_POTATO);
        simpleItem(ModItems.CANCHAN_POTATO);
        simpleItem(ModItems.BLACK_POTATO);
        simpleBlockItem(ModBlocks.WILD_VARIANTS_POTATOES);
        simpleBlockItem(ModBlocks.WILD_PURPLE_ONIONS);
        fenceItem(ModBlocks.LEMON_TREE_FENCE, ModBlocks.LEMON_PLANKS);
        buttonItem(ModBlocks.LEMON_TREE_BUTTON, ModBlocks.LEMON_PLANKS);
        evenSimplerBlockItem(ModBlocks.LEMON_TREE_STAIRS);
        evenSimplerBlockItem(ModBlocks.LEMON_TREE_SLAB);
        evenSimplerBlockItem(ModBlocks.LEMON_TREE_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.LEMON_TREE_FENCE_GATE);


        Set<Item> spriteBlockItems = Sets.newHashSet(
                ModItems.FRIED_CUY_BLOCK.get()
        );
        takeAll(items, spriteBlockItems.toArray(new Item[0])).forEach(item -> withExistingParent(itemName(item), GENERATED).texture("layer0", resourceItem(itemName(item))));










    }

    private String itemName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }


    public ResourceLocation resourceItem(String path) {
        return new ResourceLocation(PeruMod.MOD_ID, "item/" + path);
    }


    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Collection<T> takeAll(Set<? extends T> src, T... items) {
        List<T> ret = Arrays.asList(items);
        for (T item : items) {
            if (!src.contains(item)) {
                PeruMod.LOGGER.warn("Item {} not found in set", item);
            }
        }
        if (!src.removeAll(ret)) {
            PeruMod.LOGGER.warn("takeAll array didn't yield anything ({})", Arrays.toString(items));
        }
        return ret;
    }

    public static <T> Collection<T> takeAll(Set<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList<>();

        Iterator<T> iter = src.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            PeruMod.LOGGER.warn("takeAll predicate yielded nothing", new Throwable());
        }
        return ret;
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(PeruMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(PeruMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(PeruMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }


    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PeruMod.MOD_ID,"item/" + item.getId().getPath()));
    }


    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }


    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PeruMod.MOD_ID,"block/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PeruMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
