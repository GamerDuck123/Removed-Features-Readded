package com.gamerduck.rfr.entity.goals;

import com.gamerduck.rfr.RFRMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;

public class AvoidExplosionGoal<T extends PathAwareEntity> extends FleeEntityGoal<T> {
    private final PathAwareEntity ent;

    public AvoidExplosionGoal(PathAwareEntity ent, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(ent, fleeFromType, distance, slowSpeed, fastSpeed);
        this.ent = ent;
    }

    public boolean canStart() {
        if (ent.getWorld().getGameRules().getBoolean(RFRMod.SHOULD_MOBS_AVOID_CREEPERS) &&
                super.canStart() && this.targetEntity instanceof CreeperEntity creeper)
            return creeper.getFuseSpeed() == 1 || creeper.isIgnited();
        else return false;
    }

    public void start() {
        ent.setTarget(null);
        super.start();
    }

    public void tick() {
        ent.setTarget(null);
        super.tick();
    }
}
