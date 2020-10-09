package dev.mauritzen.norsecraft.items;

import dev.mauritzen.norsecraft.Norsecraft;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class IdunApple extends Item {
	public IdunApple() {
		super(new Item.Properties()
				.group(Norsecraft.TAB)
				.food(new Food.Builder()
						.hunger(4)
						.saturation(1.2f)
						// Modification from tutorial series due to deprecation of old method
						.effect(() -> new EffectInstance(Effects.HEALTH_BOOST, 15*20, 1), 1f)  
						.build())
				);
	}
}
