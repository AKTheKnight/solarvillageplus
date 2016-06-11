package com.aktheknight.solarvillageplus.init;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by alex_ on 25/03/2016.
 */
public class ModItems {

	public static void init() {

	}

	static void registerItem(Item item, String name) {
		GameRegistry.register(item, new ResourceLocation(SolarVillagePlus.MODID + ":" + name));
	}
}
