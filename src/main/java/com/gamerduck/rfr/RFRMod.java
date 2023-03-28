package com.gamerduck.rfr;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RFRMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("rfr");

	@Override
	public void onInitialize() {
		LOGGER.info("RFR Loaded...");
	}
}
