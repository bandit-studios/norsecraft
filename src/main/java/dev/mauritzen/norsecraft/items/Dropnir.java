package dev.mauritzen.norsecraft.items;

import java.util.List;

import javax.annotation.Nullable;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Dropnir extends Item{
	
	public Dropnir() {
		super(new Item.Properties().group(Norsecraft.TAB));
	}
	
   /**
    * allows items to add custom lines of information to the mouseover description
    */
   @OnlyIn(Dist.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	   super.addInformation(stack, worldIn, tooltip, flagIn);
	   if (KeyboardHelper.isHoldingShift()) {
		 tooltip.add(new StringTextComponent("Odin's armring."));
		 tooltip.add(new StringTextComponent("Generates a gold nugget every in-game day"));  
	   } else {
		 tooltip.add(new StringTextComponent("Hold shift for more information"));  
	   }
   }
   
   /**
    * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
    * update it's contents.
    */
   public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
   }
}
