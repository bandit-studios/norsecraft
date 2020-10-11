package dev.mauritzen.norsecraft.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap.MutableAttribute;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.world.World;

/**
 * Hugin and Munin are Odin's Ravens.  The Parrot is probably a good starting point for these.
 *
 */
public class RavenEntity extends ParrotEntity {

	public static final double MAX_HEALTH = 12.0D;
	public static final double MOVEMENT_SPEED = 0.5D;
	public static final double FLYING_SPEED = 0.5D;
	public static final double KNOCKBACK_RESISTANCE = 0.5D;
	
	public RavenEntity(EntityType<? extends ParrotEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	
	public static MutableAttribute setCustomAttributes() {
		// Maps to registerAttributes later
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, MAX_HEALTH)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
				.createMutableAttribute(Attributes.FLYING_SPEED, FLYING_SPEED)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, KNOCKBACK_RESISTANCE);
	}

}
