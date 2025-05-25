package net.matt.perumod.block.custom.mortar;

import net.matt.perumod.PeruMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
  public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, PeruMod.MOD_ID);

  public static final RegistryObject<MenuType<MortarMenu>> MORTAR = MENU_TYPES.register("mortar", () -> IForgeMenuType.create(MortarMenu::new));
}
