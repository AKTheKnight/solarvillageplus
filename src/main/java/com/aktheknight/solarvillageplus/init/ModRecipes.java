package com.aktheknight.solarvillageplus.init;

import com.aktheknight.solarvillageplus.common.recipes.RecipeLoader;

public class ModRecipes {
	
	public static void init() {
		shapeless();
		shaped();
		RecipeLoader.init();
	}
	
	private static void shapeless() {
		
	}
	
	private static void shaped() {
		//Items
		//GameRegistry.addRecipe(new ItemStack(ModItems.shrinkGem), "##", "##", '#', ModItems.shrinkShard);
		//SuperMeal
	}
}
