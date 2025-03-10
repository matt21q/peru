package net.matt.perumod.datagen.loot;

import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.block.custom.CornCropBlock;
import net.matt.perumod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
    this.dropSelf(ModBlocks.MUD_STOVE.get());
        this.dropSelf(ModBlocks.LEMON_TREE_STAIRS.get());
        this.dropSelf(ModBlocks.LEMON_TREE_BUTTON.get());
        this.dropSelf(ModBlocks.LEMON_TREE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.LEMON_TREE_TRAPDOOR.get());
        this.dropSelf(ModBlocks.LEMON_TREE_FENCE.get());
        this.dropSelf(ModBlocks.LEMON_TREE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.LEMON_TREE_SLAB.get());













        this.add(ModBlocks.SALT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SALT_ORE.get(), ModItems.SALT.get()));

        this.add(ModBlocks.SALT_SAND_ORE.get(),
                block -> createSandLikeOreDrops(ModBlocks.SALT_SAND_ORE.get(), ModItems.SALT.get()));
        this.dropSelf(ModBlocks.LEMON_SAPLING.get() );

        this.add(ModBlocks.LEMON_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.LEMON_TREE_TRAPDOOR.get());

        this.add(ModBlocks.LEMON_TREE_DOOR.get(),
                block -> createDoorTable(ModBlocks.LEMON_TREE_DOOR.get()));

        LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 7))
                .or(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(ModBlocks.CORN_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CornCropBlock.AGE, 6)));

        this.add(ModBlocks.CORN_CROP.get(), createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN_KERNELS.get(),
                ModItems.CORN.get(), lootitemcondition$builder2));

    this.dropSelf(ModBlocks.LEMON_LOG.get());
    this.dropSelf(ModBlocks.LEMON_WOOD.get());
    this.dropSelf(ModBlocks.STRIPPED_LEMON_LOG.get());
    this.dropSelf(ModBlocks.STRIPPED_LEMON_WOOD.get());
    this.dropSelf(ModBlocks.LEMON_PLANKS.get());
        this.add(ModBlocks.LEMON_LEAVES_WITH_LEMONS.get(), block ->
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModItems.LEMON.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(3, 4)))
                                )
                        )
        );
        this.add(ModBlocks.WILD_PURPLE_ONIONS.get(), block ->
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                // Este ítem siempre se entrega
                                .add(LootItem.lootTableItem(ModItems.PURPLE_ONION.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                )
                                // Este ítem tiene una probabilidad de aparecer
                                .add(LootItem.lootTableItem(Items.PINK_TULIP)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
                                )
                        )
        );
       this.dropSelf(ModBlocks.FRIED_CUY_BLOCK.get());
        this.add(ModBlocks.LEMON_SIGN.get(), block ->

                createSingleItemTable(ModItems.LEMON_SIGN.get()));
        this.add(ModBlocks.LEMON_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.LEMON_SIGN.get()));
        this.add(ModBlocks.LEMON_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.LEMON_HANGING_SIGN.get()));
        this.add(ModBlocks.LEMON_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.LEMON_HANGING_SIGN.get()));

        this.add(ModBlocks.WILD_VARIANTS_POTATOES.get(), block -> createWildVariantsPotatoesDrops());
    }



    private LootTable.Builder createWildVariantsPotatoesDrops() {
        LootPool.Builder pool = LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.BLACK_POTATO.get()).setWeight(1)) // 25%
                .add(LootItem.lootTableItem(ModItems.CANCHAN_POTATO.get()).setWeight(1)) // 25%
                .add(LootItem.lootTableItem(ModItems.PERUVIAN_POTATO.get()).setWeight(1)) // 25%
                .add(LootItem.lootTableItem(Items.POTATO).setWeight(1)); // 25%

        return LootTable.lootTable().withPool(pool);
    }

    protected LootTable.Builder createSandLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))));
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}

