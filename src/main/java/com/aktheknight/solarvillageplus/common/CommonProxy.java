package com.aktheknight.solarvillageplus.common;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import com.aktheknight.solarvillageplus.ConfigHandler;
import com.aktheknight.solarvillageplus.common.config.Config;
import com.aktheknight.solarvillageplus.init.ModBlocks;
import com.aktheknight.solarvillageplus.init.ModItems;
import com.aktheknight.solarvillageplus.init.ModRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class CommonProxy {
	
	public static Logger LOGGER = LogManager.getLogger(SolarVillagePlus.MODID);
	
	public void preInit(FMLPreInitializationEvent e) {
		LOGGER.log(Level.INFO, "Starting config preInit");

		final File folder = new File(e.getModConfigurationDirectory(), "SolarVillagePlus/");

		if (!folder.exists())
			folder.mkdir();

		registerConfiguration(new File(folder, "Config.cfg"));

		LOGGER.log(Level.INFO, "Finished config preInit");

		LOGGER.log(Level.INFO, "Starting items preInit");
		ModItems.init();
		LOGGER.log(Level.INFO, "Finished items preInit");



		LOGGER.log(Level.INFO, "Starting blocks preInit");
		ModBlocks.preInit();
		LOGGER.log(Level.INFO, "Finished blocks preInit");

	}
	
	public void init(FMLInitializationEvent e) {
		LOGGER.log(Level.INFO, "Starting recipes init");
		ModRecipes.init();
		LOGGER.log(Level.INFO, "Finished recipes init");

	/*	LOGGER.log(Level.INFO, "Starting WorldGen setup");
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 1);
		LOGGER.log(Level.INFO, "Finished WorldGen setup"); */
	}
	
	public void postInit(FMLPostInitializationEvent e) {
		
	}

	public void registerRenders() {

	}

	public void registerConfiguration(File configFile) {
		SolarVillagePlus.configuration = Config.initConfig(configFile);
	}
}
