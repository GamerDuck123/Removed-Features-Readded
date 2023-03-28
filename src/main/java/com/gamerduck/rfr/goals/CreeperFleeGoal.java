package com.gamerduck.rfr.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.WolfEntity;

public class CreeperFleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
    private final HostileEntity ent;

    public CreeperFleeGoal(HostileEntity ent, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(ent, fleeFromType, distance, slowSpeed, fastSpeed);
        this.ent = ent;
    }

    public boolean canStart() {
        if (super.canStart() && this.targetEntity instanceof CreeperEntity creeper) {
            return creeper.getFuseSpeed() == 1 || creeper.isIgnited();
        } else {
            return false;
        }
    }

    public void start() {
        ent.setTarget((LivingEntity)null);
        super.start();
    }

    public void tick() {
        ent.setTarget((LivingEntity)null);
        super.tick();
    }
}
