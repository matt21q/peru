package net.matt.perumod.advancment;

import net.matt.perumod.PeruMod;
import net.matt.perumod.block.ModBlocks;
import net.matt.perumod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class PeruAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator{
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {

        Advancement PerusDelight = Advancement.Builder.advancement()
                .display(ModBlocks.MUD_STOVE.get(),
                        PeruTextUtils.getTranslation("advancement.root"),
                        PeruTextUtils.getTranslation("advancement.root.desc"),
                        new ResourceLocation("minecraft:textures/block/mud_bricks.png"),
                        FrameType.TASK, false, false, false)
                .addCriterion("seeds", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{}))
                .save(consumer, getNameId("main/root"));

        Advancement nationalFood = getAdvancement(PerusDelight, ModItems.CEVICHE.get(), "get_ceviche", FrameType.TASK, true, true, false)
                .addCriterion("ceviche", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CEVICHE.get()))
                .requirements(RequirementsStrategy.OR)
                .save(consumer, getNameId("main/get_ceviche"));









    }
    protected static Advancement.Builder getAdvancement(Advancement parent, ItemLike display, String name, FrameType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(display,
                PeruTextUtils.getTranslation("advancement." + name),
                PeruTextUtils.getTranslation("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
    }

    private String getNameId(String id) {
        return PeruMod.MOD_ID + ":" + id;
    }
}
