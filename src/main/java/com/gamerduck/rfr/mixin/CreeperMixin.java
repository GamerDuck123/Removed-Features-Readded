package com.gamerduck.rfr.mixin;

import com.gamerduck.rfr.RFRMod;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperMixin {
    private final CreeperEntity me = ((CreeperEntity) (Object) this);
    @Inject(
            method = "tick()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/CreeperEntity;setFuseSpeed(I)V",
                    shift = At.Shift.AFTER
            )
    )
    private void injected(CallbackInfo ci) {
        me.getWorld().getOtherEntities(me, Box.of(me.getPos(), 8, 8, 8))
                .stream().filter(ent -> ent instanceof HostileEntity).forEach(ent -> {
                    HostileEntity hent = (HostileEntity)ent;
                    Vec3d vec3d = NoPenaltyTargeting.findFrom(me, 16, 7, hent.getPos());
                    Path fleePath;
                    if (vec3d == null) {
                        return;
                    } else if (hent.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < hent.squaredDistanceTo(me)) {
                        return;
                    } else {
                        fleePath = hent.getNavigation().findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                    }
                    if (hent.getNavigation().isIdle()) hent.getNavigation().startMovingAlong(fleePath, 2);
                });

//        }
    }
}
