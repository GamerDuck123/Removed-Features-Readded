package com.gamerduck.rfr.entity.goals;

import com.gamerduck.rfr.entity.HerobrineEntity;
import com.gamerduck.rfr.entity.SteveEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class SteveAttackGoal extends MeleeAttackGoal {
    private final SteveEntity steve;
    private int ticks;

    public SteveAttackGoal(SteveEntity steve, double speed, boolean pauseWhenMobIdle) {
        super(steve, speed, pauseWhenMobIdle);
        this.steve = steve;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.steve.setAttacking(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) this.steve.setAttacking(true);
        else this.steve.setAttacking(false);
    }
}
