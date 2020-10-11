package dev.mauritzen.norsecraft.entities;

import javax.annotation.Nullable;

import dev.mauritzen.norsecraft.util.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BoarEntity extends AnimalEntity{
	
	// Boar Constants
	public static final double MAX_HEALTH = 12.0D;
	public static final double MOVEMENT_SPEED = 0.5D;
	public static final double KNOCKBACK_RESISTANCE = 0.5D;
	
	public static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO);		

	public static final int XP_DROP_MAX = 4;
	public static final int XP_DROP_MIN = 4;

	
	private EatGrassGoal eatGrassGoal;
	private int eatGrassTimer;
	
	
	public BoarEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	/**
	 * Register custom goals regarding the boars.
	 * 
	 */
	@Override
	protected void registerGoals() {
		// Register superclass goals
		super.registerGoals();
		
		// Instantiate eat grass goal
		this.eatGrassGoal = new EatGrassGoal(this);
		
		// Add our own goals
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
		this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(1,  new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(1,  new LookRandomlyGoal(this));
		this.goalSelector.addGoal(1,  this.eatGrassGoal);
	}
	
	/**
	 * Return a random amount of xp
	 */
	@Override
	protected int getExperiencePoints(PlayerEntity player) {
		return XP_DROP_MIN + this.world.rand.nextInt(XP_DROP_MAX - XP_DROP_MIN + 1);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PIG_AMBIENT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_PIG_DEATH;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_PIG_HURT;
	}
	
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
	}

	
	
	@Override
	protected void updateAITasks() {
		// TODO Auto-generated method stub
		this.eatGrassTimer = this.eatGrassGoal.getEatingGrassTimer();
		super.updateAITasks();

	}

	
	@Override
	public void livingTick() {
		// Decrement the eatGrassTimer
		if (this.world.isRemote) {
			this.eatGrassTimer = Math.max(0,  this.eatGrassTimer - 1);
		}
		super.livingTick();
	}
	
	// Not really sure wtf they are doing here.
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id==10) {
			this.eatGrassTimer = 40;
		} else {
			super.handleStatusUpdate(id);
		}
	}


	/**
	 * Sets the attributes of the boar.
	 * @return
	 */
	public static MutableAttribute setCustomAttributes() {
		// Maps to registerAttributes later
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, MAX_HEALTH)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE);
	}
	
	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		return RegistryHandler.BOAR_ENTITY.get().create(this.world);
	}

}
