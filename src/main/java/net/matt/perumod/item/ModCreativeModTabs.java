package net.matt.perumod.item;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PeruMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PERU_TAB = CREATIVE_MODE_TABS.register("peru_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CEVICHE.get()))
                    .title(Component.translatable("creativetab.peru_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.SALT.get());
                        pOutput.accept(ModBlocks.MUD_STOVE.get());
                        pOutput.accept(ModBlocks.SALT_ORE.get());
                        pOutput.accept(ModBlocks.SALT_SAND_ORE.get());
                        pOutput.accept(ModItems.CORN.get());
                        pOutput.accept(ModItems.CORN_KERNELS.get());
                        pOutput.accept(ModItems.FRENCH_FRIES.get());
                        pOutput.accept(ModItems.SAUSAGE.get());
                        pOutput.accept(ModItems.COOKED_SAUSAGE.get());
                        pOutput.accept(ModItems.SALCHIPAPA.get());
                        pOutput.accept(ModItems.SWEET_POTATO.get());
                        pOutput.accept(ModItems.BAKED_SWEET_POTATO.get());
                        pOutput.accept(ModItems.PERUVIAN_POTATO.get());
                        pOutput.accept(ModItems.CANCHAN_POTATO.get());
                        pOutput.accept(ModItems.BLACK_POTATO.get());
                        pOutput.accept(ModItems.POISONOUS_SWEET_POTATO.get());
                        pOutput.accept(ModItems.PURPLE_ONION.get());
                        pOutput.accept(ModItems.CEVICHE.get());
                        pOutput.accept(ModItems.CUY_PERUANO_SPANW_EGG.get());
                        pOutput.accept(ModItems.CUY_ANDINO_SPANW_EGG.get());
                        pOutput.accept(ModItems.CUY_INTI_SPANW_EGG.get());
                        pOutput.accept(ModItems.LEMON.get());
                        pOutput.accept(ModItems.LEMON_SLICE.get());
                        pOutput.accept(ModBlocks.LEMON_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_LEMON_LOG.get());
                        pOutput.accept(ModBlocks.LEMON_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_LEMON_WOOD.get());
                        pOutput.accept(ModBlocks.LEMON_PLANKS.get());
                        pOutput.accept(ModBlocks.LEMON_LEAVES.get());
                        pOutput.accept(ModBlocks.LEMON_LEAVES_WITH_LEMONS.get());
                        pOutput.accept(ModBlocks.LEMON_SAPLING.get());
                        pOutput.accept(ModItems.LEMON_SIGN.get());
                        pOutput.accept(ModItems.LEMON_HANGING_SIGN.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_DOOR.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_TRAPDOOR.get());
                        pOutput.accept(ModItems.FRIED_CUY_BLOCK.get());

                        pOutput.accept(ModBlocks.LEMON_TREE_BUTTON.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_FENCE.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_SLAB.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_STAIRS.get());
                        pOutput.accept(ModBlocks.LEMON_TREE_FENCE_GATE.get());

                        pOutput.accept(ModItems.THE_CONDOR_PASSES_MUSIC_DISC.get());
                        pOutput.accept(ModBlocks.WILD_VARIANTS_POTATOES.get());
                        pOutput.accept(ModBlocks.WILD_PURPLE_ONIONS.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
