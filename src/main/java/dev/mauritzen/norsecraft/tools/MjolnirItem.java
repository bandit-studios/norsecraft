package dev.mauritzen.norsecraft.tools;

import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk.CreateEntityType;

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
			target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, attackDamage);
			World worldIn = target.getEntityWorld();
			
		
			
		    return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		// TODO Auto-generated method stub
		
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		Vector3d vector3d = playerIn.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
		float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = 1000;
		Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
		BlockRayTraceResult result = worldIn.rayTraceBlocks(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, FluidMode.ANY, playerIn));

		BlockPos target = result.getPos();
		//Vector3d target = result.getHitVec();
		LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(worldIn);
		lightningboltentity.moveForced(target.getX(), target.getY(), target.getZ());
        worldIn.addEntity(lightningboltentity);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
