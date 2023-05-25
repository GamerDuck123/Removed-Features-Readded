package com.gamerduck.rfr.mixin;


import com.gamerduck.rfr.entity.goals.AvoidExplosionGoal;
import com.gamerduck.rfr.entity.goals.GiantAttackGoal;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(GiantEntity.class)
public class GiantAIMixin {
    private final GiantEntity me = ((GiantEntity) (Object) this);

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void injected(CallbackInfo ci) {
        me.goalSelector.add(8, new LookAtEntityGoal(me, PlayerEntity.class, 8.0f));
        me.goalSelector.add(8, new LookAroundGoal(me));
        me.goalSelector.add(2, new GiantAttackGoal(me, 1.0, false));
        me.goalSelector.add(6, new MoveThroughVillageGoal(me, 1.0, true, 4, () -> true));
        me.goalSelector.add(7, new WanderAroundFarGoal(me, 1.0));
        me.targetSelector.add(1, new RevengeGoal(me, new Class[0]).setGroupRevenge(ZombifiedPiglinEntity.class));
        me.targetSelector.add(2, new ActiveTargetGoal<PlayerEntity>(me, PlayerEntity.class, true));
        me.targetSelector.add(3, new ActiveTargetGoal<MerchantEntity>(me, MerchantEntity.class, false));
        me.targetSelector.add(3, new ActiveTargetGoal<IronGolemEntity>(me, IronGolemEntity.class, true));

    }

    @Inject(method = "createGiantAttributes", at = @At(value = "RETURN"), cancellable = true)
    private static void inject(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.setReturnValue(HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 75.0).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.45f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0).add(EntityAttributes.GENERIC_ARMOR, 2.0).add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS));
    }

}