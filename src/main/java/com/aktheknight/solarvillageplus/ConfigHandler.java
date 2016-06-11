package com.aktheknight.solarvillageplus;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
	
	public static Configuration config;

    public static String CATEGORY_BLOCKS = "blocks";
    public static String CATEGORY_ITEMS = "items";

    //Blocks
    public static int SuperGrowthAccRadius;
    //Items
    public static int DirtyHoeRadius;
    public static int SuperMealRadius;
    public static int SuperMealOutputAmount;



	public static void init(File configFile) {
		config = new Configuration(configFile);
		loadConfig();
	}
	    
	private static void loadConfig() {
        SuperGrowthAccRadius = config.get(CATEGORY_BLOCKS, "SuperGrowthAccelerator radius", 1, "Radius of effect of the SuperGrowthAccelerator").getInt();

        DirtyHoeRadius = config.get(CATEGORY_ITEMS, "DirtyHoe radius", 1, "Radius of effect of the DirtyHoe").getInt();
        SuperMealRadius = config.get(CATEGORY_ITEMS, "SuperMeal radius", 2, "Radius of effect of the SuperMeal").getInt();
        SuperMealOutputAmount = config.get(CATEGORY_ITEMS, "SuperMeal ouput amount", 3, "How many supermeal should the recipe give?").getInt();

		if (config.hasChanged()) {
			config.save();
		}
	}
}
