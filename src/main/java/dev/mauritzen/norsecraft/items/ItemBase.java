package dev.mauritzen.norsecraft.items;

import dev.mauritzen.norsecraft.Norsecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

	public ItemBase() {
		super(new Item.Properties().group(Norsecraft.TAB));
	}
	
	public ItemBase(Properties properties) {
		super(properties);
	}
	
}
