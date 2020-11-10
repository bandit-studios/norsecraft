package dev.mauritzen.norsecraft.entities;
import javax.annotation.Nullable;

import dev.mauritzen.norsecraft.util.RegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MjolnirEntity extends AbstractArrowEntity {

	private ItemStack thrownStack = new ItemStack(Items.TRIDENT);
	private boolean dealtDamage;
	public int returningTicks;

	public MjolnirEntity(EntityType<? extends MjolnirEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public MjolnirEntity(World worldIn, LivingEntity thrower, ItemStack thrownStackIn) {
		super(EntityType.ARROW, thrower, worldIn);
		//this.thrownStack = thrownStackIn.copy();
	}

	@OnlyIn(Dist.CLIENT)
	public MjolnirEntity(World worldIn, double x, double y, double z) {
		super(EntityType.ARROW, x, y, z, worldIn);
	}

	protected void registerData() {
		super.registerData();
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		if (this.timeInGround > 4) {
			this.dealtDamage = true;
		}

		Entity entity = this.func_234616_v_();
		if ((this.dealtDamage || this.getNoClip()) && entity != null) {
			double loyaltyLev = 2;
			this.setNoClip(true);
			Vector3d vector3d = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
			this.setRawPosition(this.getPosX(), this.getPosY() + vector3d.y * 0.015D * (double)loyaltyLev, this.getPosZ());
			if (this.world.isRemote) {
				this.lastTickPosY = this.getPosY();
			}

			double d0 = 0.05D * (double)loyaltyLev;
			this.setMotion(this.getMotion().scale(0.95D).add(vector3d.normalize().scale(d0)));
			if (this.returningTicks == 0) {
				this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
			}

			++this.returningTicks;

		}

		super.tick();
	}

	private boolean shouldReturnToThrower() {
		Entity entity = this.func_234616_v_();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
		} else {
			return false;
		}
	}


	protected ItemStack getArrowStack() {
		return this.thrownStack.copy();
	}

	/*
	   @OnlyIn(Dist.CLIENT)
	   public boolean func_226572_w_() {
	      return this.dataManager.get(field_226571_aq_);
	   }
	 */

	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
		return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
	}

	/**
	 * Called when the arrow hits an entity
	 */
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) {
		Entity entity = p_213868_1_.getEntity();
		float f = 8.0F;
		if (entity instanceof LivingEntity) {
			LivingEntity livingentity = (LivingEntity)entity;
			f += EnchantmentHelper.getModifierForCreature(this.thrownStack, livingentity.getCreatureAttribute());
		}

		Entity entity1 = this.func_234616_v_();
		DamageSource damagesource = DamageSource.causeTridentDamage(this, (Entity)(entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.ITEM_TRIDENT_HIT;
		if (entity.attackEntityFrom(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity1 = (LivingEntity)entity;
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.applyThornEnchantments(livingentity1, entity1);
					EnchantmentHelper.applyArthropodEnchantments((LivingEntity)entity1, livingentity1);
				}

				this.arrowHit(livingentity1);
			}
		}

		this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;
		if (this.world instanceof ServerWorld && this.world.isThundering() && EnchantmentHelper.hasChanneling(this.thrownStack)) {
			BlockPos blockpos = entity.getPosition();
			if (this.world.canSeeSky(blockpos)) {
				LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.world);
				lightningboltentity.moveForced(Vector3d.copyCenteredHorizontally(blockpos));
				lightningboltentity.setCaster(entity1 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity1 : null);
				this.world.addEntity(lightningboltentity);
				soundevent = SoundEvents.ITEM_TRIDENT_THUNDER;
				f1 = 5.0F;
			}
		}

		this.playSound(soundevent, f1, 1.0F);
	}

	/**
	 * The sound made when an entity is hit by this projectile
	 */
	protected SoundEvent getHitEntitySound() {
		return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		Entity entity = this.func_234616_v_();
		if (entity == null || entity.getUniqueID() == entityIn.getUniqueID()) {
			super.onCollideWithPlayer(entityIn);
		}
	}



	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.put("Trident", this.thrownStack.write(new CompoundNBT()));
		compound.putBoolean("DealtDamage", this.dealtDamage);
	}

	public void func_225516_i_() {
		int i = 2;
		if (this.pickupStatus != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) {
			super.func_225516_i_();
		}

	}

	protected float getWaterDrag() {
		return 0.99F;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}
}
