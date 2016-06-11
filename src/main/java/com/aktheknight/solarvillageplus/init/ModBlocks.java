package com.aktheknight.solarvillageplus.init;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class ModBlocks {

	//public static Block shrinkOre;

    public static void init() {
    }

    static void registerBlock(Block block, String name, boolean itemblock) {
        GameRegistry.register(block, new ResourceLocation(SolarVillagePlus.MODID + ":" + name));
        if (itemblock) {
            GameRegistry.register(new ItemBlock(block), new ResourceLocation(SolarVillagePlus.MODID + ":" + name));
        }
    }

    static void registerTile(Block block, String name, Class clazz, boolean itemblock) {
        registerBlock(block, name, itemblock);
        GameRegistry.registerTileEntity(clazz, name);
    }

}
