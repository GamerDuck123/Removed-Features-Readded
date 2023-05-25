package com.gamerduck.rfr.entity;

import com.gamerduck.rfr.entity.goals.AvoidExplosionGoal;
import com.gamerduck.rfr.entity.goals.HerobrineAttackGoal;
import com.gamerduck.rfr.entity.goals.SteveAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SteveEntity extends PathAwareEntity {

    public SteveEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new AvoidExplosionGoal(this, CreeperEntity.class, 24.0F, 1.5, 1.5));
        this.goalSelector.add(3, new SteveAttackGoal(this, 1.0, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, HostileEntity.class, 8.0F));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.targetSelector.add(1, new ActiveTargetGoal(this, HostileEntity.class, true));
    }


    public static DefaultAttributeContainer.Builder createSteveAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.27000000417232513)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 4.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.2);
    }

}
