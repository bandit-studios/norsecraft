package dev.mauritzen.norsecraft.client.render;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.client.model.BoarModel;
import dev.mauritzen.norsecraft.client.model.RavenModel;
import dev.mauritzen.norsecraft.entities.BoarEntity;
import dev.mauritzen.norsecraft.entities.RavenEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RavenRenderer extends MobRenderer<RavenEntity, RavenModel<RavenEntity>> {

    public RavenRenderer(EntityRendererManager renderManagerIn, RavenModel<RavenEntity> entityModelIn, float shadowSizeIn) {
    	super(renderManagerIn, new RavenModel<>(), 0.7f);
	}
    
    public RavenRenderer(EntityRendererManager renderManagerIn) {
    	super(renderManagerIn, new RavenModel<>(), 0.7f);
	}

	protected static final ResourceLocation TEXTURE = new ResourceLocation(Norsecraft.MOD_ID, "textures/entity/raven.png");


    @Override
    public ResourceLocation getEntityTexture(RavenEntity entity) {
        return TEXTURE;
    }
}