package dev.mauritzen.norsecraft.tools;

import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.DamageSource;

public class MjolnirItem extends TieredItem {

	private final float attackDamage;
	
	public MjolnirItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties itemProperties) {
		super(itemTier, itemProperties);
		
		this.attackDamage = itemTier.getAttackDamage() + attackDamage; 
		
	}


	public float getAttackDamage() {
		return attackDamage;
	}
	
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, attackDamage*100);
		    return true;
	}
	
}
