package com.gamerduck.rfr.blocks;

import com.gamerduck.rfr.RFRMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import static com.gamerduck.rfr.RFRMod.*;

public class RFRBlocks {

    public static final SlabBlock DIRT_SLAB;
    public static final Block RUBY_ORE;
    public static final Block DEEPSLATE_RUBY_ORE;
    public static final Block RUBY_BLOCK;

    public RFRBlocks() {}

    private static <T> T register(String id, T item) {
        return (T) Registry.register(Registries.BLOCK, new Identifier(modid, id), (Block) item);
    }

    static {
        DIRT_SLAB = register("dirt_slab", new SlabBlock(FabricBlockSettings.of(Material.SOIL, MapColor.DIRT_BROWN).strength(0.5f, 0.5f).sounds(BlockSoundGroup.GRAVEL)));

        RUBY_ORE = register("ruby_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F), UniformIntProvider.create(3, 7)));
        DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(RUBY_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(3, 7)));
        RUBY_BLOCK = register("ruby_block", new Block(AbstractBlock.Settings.of(Material.METAL, MapColor.DULL_RED).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL)));

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(modid,"ore_ruby")));
    }
}
