package com.gamerduck.rfr.entity;

import com.gamerduck.rfr.entity.goals.AvoidExplosionGoal;
import com.gamerduck.rfr.entity.goals.HerobrineAttackGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HerobrineEntity extends HostileEntity {

    public HerobrineEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new AvoidExplosionGoal(this, CreeperEntity.class, 24.0F, 1.5, 1.5));
        this.goalSelector.add(3, new HerobrineAttackGoal(this, 1.0, false));
        this.goalSelector.add(6, new MoveThroughVillageGoal(this, 1.0, false, 4, () -> true));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(8, new LookAtEntityGoal(this, LivingEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal(this, LivingEntity.class, true));
    }


    public static DefaultAttributeContainer.Builder createHerobrineAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 60.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.27000000417232513)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2)
                .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);

    }

}
