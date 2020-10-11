package dev.mauritzen.norsecraft.client.render;

import dev.mauritzen.norsecraft.Norsecraft;
import dev.mauritzen.norsecraft.client.model.BoarModel;
import dev.mauritzen.norsecraft.entities.BoarEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BoarRenderer extends MobRenderer<BoarEntity, BoarModel<BoarEntity>> {

    public BoarRenderer(EntityRendererManager renderManagerIn, BoarModel<BoarEntity> entityModelIn, float shadowSizeIn) {
    	super(renderManagerIn, new BoarModel<>(), 0.7f);
	}
    
    public BoarRenderer(EntityRendererManager renderManagerIn) {
    	super(renderManagerIn, new BoarModel<>(), 0.7f);
	}

	protected static final ResourceLocation TEXTURE = new ResourceLocation(Norsecraft.MOD_ID, "textures/entity/boar.png");


    @Override
    public ResourceLocation getEntityTexture(BoarEntity entity) {
        return TEXTURE;
    }
}