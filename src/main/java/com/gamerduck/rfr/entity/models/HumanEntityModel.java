package com.gamerduck.rfr.entity.models;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.random.Random;

import java.util.List;

public class HumanEntityModel<T extends LivingEntity> extends BipedEntityModel<T> {
    private final List<ModelPart> parts;
    public final ModelPart leftSleeve;
    public final ModelPart rightSleeve;
    public final ModelPart leftPants;
    public final ModelPart rightPants;
    public final ModelPart jacket;
    private final ModelPart cloak;

    public HumanEntityModel(ModelPart root) {
        super(root, RenderLayer::getEntityTranslucent);
        this.cloak = root.getChild("cloak");
        this.leftSleeve = root.getChild("left_sleeve");
        this.rightSleeve = root.getChild("right_sleeve");
        this.leftPants = root.getChild("left_pants");
        this.rightPants = root.getChild("right_pants");
        this.jacket = root.getChild("jacket");
        this.parts = (List)root.traverse().filter((part) -> {
            return !part.isEmpty();
        }).collect(ImmutableList.toImmutableList());
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
        ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("cloak", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, dilation, 1.0F, 0.5F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        modelPartData.addChild("left_sleeve", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));
        modelPartData.addChild("right_sleeve", ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        modelPartData.addChild("left_pants", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        modelPartData.addChild("right_pants", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
        modelPartData.addChild("jacket", ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation.add(0.25F)), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.leftPants, this.rightPants, this.leftSleeve, this.rightSleeve, this.jacket));
    }

    public void setAngles(T livingEntity, float f, float g, float h, float i, float j) {
        super.setAngles(livingEntity, f, g, h, i, j);
        this.leftPants.copyTransform(this.leftLeg);
        this.rightPants.copyTransform(this.rightLeg);
        this.leftSleeve.copyTransform(this.leftArm);
        this.rightSleeve.copyTransform(this.rightArm);
        this.jacket.copyTransform(this.body);
        if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).isEmpty()) {
            if (livingEntity.isInSneakingPose()) {
                this.cloak.pivotZ = 1.4F;
                this.cloak.pivotY = 1.85F;
            } else {
                this.cloak.pivotZ = 0.0F;
                this.cloak.pivotY = 0.0F;
            }
        } else if (livingEntity.isInSneakingPose()) {
            this.cloak.pivotZ = 0.3F;
            this.cloak.pivotY = 0.8F;
        } else {
            this.cloak.pivotZ = -1.1F;
            this.cloak.pivotY = -0.85F;
        }

    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        this.leftSleeve.visible = visible;
        this.rightSleeve.visible = visible;
        this.leftPants.visible = visible;
        this.rightPants.visible = visible;
        this.jacket.visible = visible;
        this.cloak.visible = visible;
    }

    public void setArmAngle(Arm arm, MatrixStack matrices) {
        ModelPart modelPart = this.getArm(arm);
        modelPart.rotate(matrices);
    }

    public ModelPart getRandomPart(Random random) {
        return (ModelPart)this.parts.get(random.nextInt(this.parts.size()));
    }
}
