package net.matt.perumod.entity.client;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.matt.perumod.entity.animation.ModAnimationsDefinitions;
import net.matt.perumod.entity.custom.CuyAndinoEntity;
import net.matt.perumod.entity.custom.CuyPeruEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CuyPeruanoModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart all;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart left_leg1;
	private final ModelPart right_leg1;
	private final ModelPart left_leg2;
	private final ModelPart right_leg2;
	private final ModelPart eyes;

	public CuyPeruanoModel(ModelPart root) {
		this.all = root.getChild("all");
		this.left_ear = this.all.getChild("left_ear");
		this.right_ear = this.all.getChild("right_ear");
		this.left_leg1 = this.all.getChild("left_leg1");
		this.right_leg1 = this.all.getChild("right_leg1");
		this.left_leg2 = this.all.getChild("left_leg2");
		this.right_leg2 = this.all.getChild("right_leg2");
		this.eyes = this.all.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.725F, 0.0F));

		PartDefinition left_ear = all.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(13, 14).addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -2.0F, -3.5F));

		PartDefinition right_ear = all.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(19, 14).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, -3.5F));

		PartDefinition left_leg1 = all.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(10, 14).addBox(-1.5F, 0.0F, -1.25F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 3.0F, -4.0F));

		PartDefinition right_leg1 = all.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(10, 14).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 3.0F, -4.75F));

		PartDefinition left_leg2 = all.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(10, 14).addBox(-1.5F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 3.0F, 2.25F));

		PartDefinition right_leg2 = all.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(10, 14).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 3.0F, 2.25F));

		PartDefinition eyes = all.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -1.0F, -5.125F, 6.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationsDefinitions.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((CuyPeruEntity) entity).idleAnimationState, ModAnimationsDefinitions.IDLE, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return all;
	}
}