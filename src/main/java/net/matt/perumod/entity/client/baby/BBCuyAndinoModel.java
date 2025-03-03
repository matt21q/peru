package net.matt.perumod.entity.client.baby;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.matt.perumod.entity.animation.ModBBAnimationsDeifinitions;
import net.matt.perumod.entity.custom.baby.BBCuyAndinoEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BBCuyAndinoModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart all;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart left_leg1;
	private final ModelPart right_leg1;
	private final ModelPart left_leg2;
	private final ModelPart right_leg2;
	private final ModelPart eyes;

	public BBCuyAndinoModel(ModelPart root) {
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

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create().texOffs(0, 16).addBox(-2.23F, -1.505F, -2.95F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.25F, 0.0F));

		PartDefinition left_ear = all.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(15, 24).addBox(-0.005F, -0.045F, -0.59F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.77F, -1.455F, -2.36F));

		PartDefinition right_ear = all.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(11, 24).addBox(-1.995F, -0.045F, -1.09F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.225F, -1.455F, -1.86F));

		PartDefinition left_leg1 = all.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(10, 24).addBox(-0.955F, 0.1033F, -0.7375F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.065F, 1.495F, -2.36F));

		PartDefinition right_leg1 = all.addOrReplaceChild("right_leg1", CubeListBuilder.create().texOffs(10, 24).addBox(-0.365F, 0.1033F, -0.295F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.065F, 1.495F, -2.8025F));

		PartDefinition left_leg2 = all.addOrReplaceChild("left_leg2", CubeListBuilder.create().texOffs(10, 24).addBox(-0.955F, 0.1033F, -0.295F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.065F, 1.495F, 1.3275F));

		PartDefinition right_leg2 = all.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(10, 24).addBox(-0.365F, 0.1033F, -0.295F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.065F, 1.495F, 1.3275F));

		PartDefinition eyes = all.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 24).addBox(-2.23F, 0.59F, -3.0238F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.275F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModBBAnimationsDeifinitions.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((BBCuyAndinoEntity) entity).idleAnimationState, ModBBAnimationsDeifinitions.IDLE, ageInTicks, 1f);
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