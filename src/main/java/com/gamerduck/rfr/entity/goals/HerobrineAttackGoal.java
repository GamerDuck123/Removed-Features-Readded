package com.gamerduck.rfr.entity.goals;

import com.gamerduck.rfr.entity.HerobrineEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Hand;

public class HerobrineAttackGoal extends MeleeAttackGoal {
    private final HerobrineEntity herobrine;
    private int ticks;

    public HerobrineAttackGoal(HerobrineEntity herobrine, double speed, boolean pauseWhenMobIdle) {
        super(herobrine, speed, pauseWhenMobIdle);
        this.herobrine = herobrine;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.herobrine.setAttacking(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.herobrine.setAttacking(true);
            this.herobrine.swingHand(Hand.MAIN_HAND);
        } else {
            this.herobrine.setAttacking(false);
        }

    }
}
