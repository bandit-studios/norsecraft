package dev.mauritzen.norsecraft.items;

import dev.mauritzen.norsecraft.Norsecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class NorseGuide extends Item {

	public NorseGuide() {
		super(new Item.Properties().group(Norsecraft.TAB));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        
		// Get the help item
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		// Display a GUI
		
		return ActionResult.resultSuccess(itemstack);
		
	}
	
}
