package com.aktheknight.solarvillageplus.init;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import com.aktheknight.solarvillageplus.blocks.SolarPanel;
import com.aktheknight.solarvillageplus.blocks.tiles.TileEntitySolarPanel;
import com.aktheknight.solarvillageplus.util.IBlockRenderer;
import com.aktheknight.solarvillageplus.util.PanelTier;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	//public static Block shrinkOre;
	public static Block panel;

    public static void preInit() {
        for (PanelTier tier : PanelTier.values()) {
            Block panel = new SolarPanel(tier);
            registerTile(panel,"panel_"+tier.name().toLowerCase(), TileEntitySolarPanel.class,true);
            if(panel instanceof IBlockRenderer) {
                ((IBlockRenderer) panel).registerBlockRenderer();
                ((IBlockRenderer) panel).registerBlockItemRenderer();
            }
        }
    }

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
