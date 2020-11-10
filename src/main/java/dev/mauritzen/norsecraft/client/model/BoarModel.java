package dev.mauritzen.norsecraft.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import dev.mauritzen.norsecraft.entities.BoarEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BoarModel<T extends BoarEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer legFrontRight;
	private final ModelRenderer legFrontLeft;
	private final ModelRenderer legBackRight;
	private final ModelRenderer legBackLeft;

	public BoarModel() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 12.0F, -6.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(2.0F, -5.0F, -8.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -8.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.setTextureOffset(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, 0.0F, false);

		legFrontRight = new ModelRenderer(this);
		legFrontRight.setRotationPoint(3.0F, 18.0F, -5.0F);
		legFrontRight.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		legFrontLeft = new ModelRenderer(this);
		legFrontLeft.setRotationPoint(-3.0F, 18.0F, -5.0F);
		legFrontLeft.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		legBackRight = new ModelRenderer(this);
		legBackRight.setRotationPoint(3.0F, 18.0F, 7.0F);
		legBackRight.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		legBackLeft = new ModelRenderer(this);
		legBackLeft.setRotationPoint(-3.0F, 18.0F, 7.0F);
		legBackLeft.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
		legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
		legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
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