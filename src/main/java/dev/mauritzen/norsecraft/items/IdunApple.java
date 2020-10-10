package dev.mauritzen.norsecraft.items;

import java.util.List;

import javax.annotation.Nullable;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * A special apple item that imbues the consumer with health boost.
 * According to mythology, these apples were eaten by the god to maintain their youth.
 *
 */
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
	

   /**
    * allows items to add custom lines of information to the mouseover description
    */
   @OnlyIn(Dist.CLIENT)
   public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	   super.addInformation(stack, worldIn, tooltip, flagIn);
	   if (KeyboardHelper.isHoldingShift()) {
		 tooltip.add(new StringTextComponent("Idun's apple of youth."));
		 tooltip.add(new StringTextComponent("Eat this and live\u00A7e forever."));  
	   } else {
		 tooltip.add(new StringTextComponent("Hold shift for more information"));  
	   }
   }
   
   public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	   if ((entityLiving instanceof PlayerEntity) && (worldIn.isRemote == false)){
		   Norsecraft.LOGGER.debug("Entity is player.");
		   ((PlayerEntity) entityLiving).sendStatusMessage(new StringTextComponent("Wow you feel young again."), false);
	   }
	   return super.onItemUseFinish(stack, worldIn, entityLiving);
   }
   
   

}
