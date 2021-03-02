package com.brammie.inconspicuousliquidmod.init;

import java.util.function.Supplier;

import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod;
import com.brammie.inconspicuousliquidmod.inconspicuousLiquidMod.CumItemGroup;
import com.brammie.inconspicuousliquidmod.objects.items.CumCollector;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class itemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
			inconspicuousLiquidMod.MOD_ID);

	public static final RegistryObject<Item> CUM_COLLECTOR = ITEMS.register("cum_collector",
			() -> new CumCollector(new Item.Properties().group(CumItemGroup.instance)));
	public static final RegistryObject<Item> CUM = ITEMS.register("cum",
			() -> new Item(new Item.Properties().group(CumItemGroup.instance)));

	public static final RegistryObject<Item> CUM_SWORD = ITEMS.register("cum_sword",
			() -> new SwordItem(ModItemTier.CUM, 8, 6.0f, new Item.Properties().group(CumItemGroup.instance)));
	public static final RegistryObject<Item> CUM_PICAXE = ITEMS.register("cum_picaxe",
			() -> new PickaxeItem(ModItemTier.CUM, 5, 6.0f, new Item.Properties().group(CumItemGroup.instance)));
	public static final RegistryObject<Item> CUM_SHOVEL = ITEMS.register("cum_shovel",
			() -> new ShovelItem(ModItemTier.CUM, 3, 6.0f, new Item.Properties().group(CumItemGroup.instance)));
	public static final RegistryObject<Item> CUM_AXE = ITEMS.register("cum_axe",
			() -> new AxeItem(ModItemTier.CUM, 14, 6.0f, new Item.Properties().group(CumItemGroup.instance)));
	public static final RegistryObject<Item> RAW_CUM = ITEMS.register("raw_cum", () -> new Item(new Item.Properties().group(CumItemGroup.instance)));

	
	
	/*event.getRegistry().register(
			new PickaxeItem(ModItemTier.EXAMPLE, 4, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
					.setRegistryName("example_pickaxe"));
	event.getRegistry().register(
			new ShovelItem(ModItemTier.EXAMPLE, 2, 5.0f, new Item.Properties().group(TutorialItemGroup.instance))
					.setRegistryName("example_shovel"));
	event.getRegistry().register(
			new AxeItem(ModItemTier.EXAMPLE, 11, 3.0f, new Item.Properties().group(TutorialItemGroup.instance))
					.setRegistryName("example_axe"));
	*/
	public enum ModItemTier implements IItemTier {
		// int harvestLevel, int maxUses, float efficiency, float attackDamage, int
		// enchantability, Supplier<Ingredient> repairMaterial
		CUM(4, 1500, 15.0F, 7.0F, 250, () -> {
			return Ingredient.fromItems(itemInit.CUM.get());
		});

		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;

		private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability,
				Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}

		@Override
		public int getMaxUses() {
			return this.maxUses;
		}

		@Override
		public float getEfficiency() {
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
	}
}
