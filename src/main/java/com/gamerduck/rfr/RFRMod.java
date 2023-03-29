package com.gamerduck.rfr;

import com.gamerduck.rfr.entity.RFREntities;
import com.gamerduck.rfr.blocks.RFRBlocks;
import com.gamerduck.rfr.items.RFRItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RFRMod implements ModInitializer {

	public static final String modid = "rfr";
	public static final Logger LOGGER = LoggerFactory.getLogger(modid);
	public static final GameRules.Key<GameRules.BooleanRule> SHOULD_MOBS_AVOID_CREEPERS =
			GameRuleRegistry.register("shouldMobsAvoidCreepers", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
	public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(modid,"ore_ruby"));

	@Override
	public void onInitialize() {
		new RFREntities();
		new RFRBlocks();
		new RFRItems();

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RUBY_ORE_PLACED_KEY);

		LOGGER.info("RFR Loaded...");
	}
}
