package com.gamerduck.rfr.mixin;

import com.gamerduck.rfr.entity.SteveEntity;
import com.gamerduck.rfr.entity.goals.AvoidExplosionGoal;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HostileEntity.class)
public class HostileMobMixin {
    private final HostileEntity me = ((HostileEntity) (Object) this);

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void injected(CallbackInfo ci) {
        me.goalSelector.add(3, new AvoidExplosionGoal(me, CreeperEntity.class, 24.0F, 1.5, 1.5));
        me.targetSelector.add(4, new ActiveTargetGoal<SteveEntity>(me, SteveEntity.class, true));
    }

}
