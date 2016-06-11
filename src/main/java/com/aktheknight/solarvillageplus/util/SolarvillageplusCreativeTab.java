package com.aktheknight.solarvillageplus.util;

import com.aktheknight.solarvillageplus.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by alex on 26/04/16.
 */
public class SolarvillageplusCreativeTab extends CreativeTabs {

    public SolarvillageplusCreativeTab(int index, String label) {
        super(index, label);
        this.setBackgroundImageName("solarvillageplus.png");
    }

    @Override
    public Item getTabIconItem() {
        //TODO
        return Item.getItemFromBlock(Block.getBlockFromName("solarvillageplus:panel_tier4"));
    }
}
