package dev.mauritzen.norsecraft.tools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraft.world.server.ServerWorld;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;



public class MjolnirItem extends TieredItem {

	private final float attackDamage;
	private final Multimap<Attribute, AttributeModifier> attributeMap;
	
	public MjolnirItem(IItemTier itemTier, int attackDamage, float attackSpeed, Properties itemProperties) {
		super(itemTier, itemProperties);
		this.attackDamage = itemTier.getAttackDamage() + attackDamage; 
		
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.field_233823_f_, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.field_233825_h_, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)attackSpeed, AttributeModifier.Operation.ADDITION));
		this.attributeMap = builder.build();

		
	}


	public float getAttackDamage() {
		return attackDamage;
	}
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (state.isIn(Blocks.STONE)) {
			ItemStack itemstack = new ItemStack(Items.GRAVEL);
            if (!itemstack.isEmpty()) {
               double d0 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
               double d1 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
               double d2 = (double)(worldIn.rand.nextFloat() * 0.5F) + 0.25D;
               ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack);
               itementity.setDefaultPickupDelay();
               worldIn.addEntity(itementity);
            }
		}

		return true;
	}

	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (state.isIn(Blocks.STONE)) {
			return 15.0F;
		} else {
			Material material = state.getMaterial();
			return material != Material.PLANTS && material != Material.TALL_PLANTS && material != Material.CORAL && !state.func_235714_a_(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}

	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		// TODO Auto-generated method stub
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		Vector3d eyePos = playerIn.getEyePosition(1.0F);
		float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
		float y = MathHelper.sin(-f * ((float)Math.PI / 180F));
		float x = f3 * f4;
		float z = f2 * f4;
		double dist = 1000;
		Vector3d vector3d1 = eyePos.add((double)x * dist, (double)y * dist, (double)z * dist);
		BlockRayTraceResult result = worldIn.rayTraceBlocks(new RayTraceContext(eyePos, vector3d1, RayTraceContext.BlockMode.OUTLINE, FluidMode.ANY, playerIn));

		BlockPos target = result.getPos();
		LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(worldIn);
		lightningboltentity.moveForced(target.getX(), target.getY(), target.getZ());
        worldIn.addEntity(lightningboltentity);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeMap : super.getAttributeModifiers(equipmentSlot);
	}

}
