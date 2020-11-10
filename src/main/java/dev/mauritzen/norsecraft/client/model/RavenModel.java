package dev.mauritzen.norsecraft.client.model;
// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import dev.mauritzen.norsecraft.entities.BoarEntity;
import dev.mauritzen.norsecraft.entities.RavenEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class RavenModel<T extends RavenEntity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer wing0;
	private final ModelRenderer wing1;
	private final ModelRenderer head;
	private final ModelRenderer tail;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;

	public RavenModel() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.5F, -3.0F);
		setRotationAngle(body, 0.4363F, 0.0F, 0.0F);
		body.setTextureOffset(2, 8).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		wing0 = new ModelRenderer(this);
		wing0.setRotationPoint(1.5F, 0.4F, 0.2F);
		body.addChild(wing0);
		setRotationAngle(wing0, 0.1745F, 0.0F, 0.0F);
		wing0.setTextureOffset(19, 8).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);

		wing1 = new ModelRenderer(this);
		wing1.setRotationPoint(-1.5F, 0.4F, 0.2F);
		body.addChild(wing1);
		setRotationAngle(wing1, 0.1745F, 0.0F, 0.0F);
		wing1.setTextureOffset(19, 8).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 15.7F, -2.8F);
		head.setTextureOffset(2, 2).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(10, 0).addBox(-1.0F, -2.5F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(11, 7).addBox(-0.5F, -1.5F, -1.9F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(16, 7).addBox(-0.5F, -1.5F, -2.9F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(2, 18).addBox(0.0F, -5.8F, -2.1F, 0.0F, 5.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 21.1F, 1.2F);
		setRotationAngle(tail, 0.8727F, 0.0F, 0.0F);
		tail.setTextureOffset(22, 1).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(1.5F, 23.0F, -0.5F);
		leg0.setTextureOffset(14, 18).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-0.5F, 23.0F, -0.5F);
		leg1.setTextureOffset(14, 18).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// TODO Auto-generated method stub
		
	}
}