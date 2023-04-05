package com.gamerduck.rfr.entity;

import com.gamerduck.rfr.RFRMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.gamerduck.rfr.RFRMod.modid;

public class RFREntities {
    public static final EntityType<SteveEntity> STEVE;
    public static final EntityType<HerobrineEntity> HEROBRINE;

    public RFREntities() {
    }

    private static <T> T register(String id, T item) {
        return (T) Registry.register(Registries.ENTITY_TYPE, new Identifier(modid, id), (EntityType) item);
    }

    static {
        HEROBRINE = register("herobrine", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HerobrineEntity::new)
                .dimensions(EntityDimensions.fixed(0.75f, 1.8f)).build());
        FabricDefaultAttributeRegistry.register(HEROBRINE, HerobrineEntity.createHerobrineAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, RFREntities.HEROBRINE, 1, 1, 1);

        STEVE = register("steve", FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SteveEntity::new)
                .dimensions(EntityDimensions.changing(0.75f, 1.8f)).build());
        FabricDefaultAttributeRegistry.register(STEVE, SteveEntity.createSteveAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, RFREntities.STEVE, 1, 1, 2);

    }

}