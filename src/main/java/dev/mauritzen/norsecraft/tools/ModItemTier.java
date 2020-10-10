package dev.mauritzen.norsecraft.tools;

import java.util.function.Supplier;

import dev.mauritzen.norsecraft.util.RegistryHandler;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.accesstransformer.generated.AtParser.Return_valueContext;

public enum ModItemTier implements IItemTier{
	GODTIER(3, 8000, 7.0F, 3.0F, 12, () -> {
		return Ingredient.fromItems(RegistryHandler.NORSE_GEM.get());
	});

	private final float attackDamage;
	private final float efficiency;
	private final int enchantability;
	private final int harvestLevel;
	private final int maxUses;
	private final Supplier<Ingredient> repairMaterial;

	
	ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.enchantability = enchantability;
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.repairMaterial = repairMaterial;
	}
	
	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return attackDamage;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return efficiency;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return enchantability;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}

	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return maxUses;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return repairMaterial.get();
	}

}
