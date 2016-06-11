package com.aktheknight.solarvillageplus.util;

import com.aktheknight.solarvillageplus.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by alex on 26/04/16.
 */
public class SolarvillageplusCreativeTab extends CreativeTabs {

    public SolarvillageplusCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public Item getTabIconItem() {
        //TODO
        return Items.APPLE;
    }
}
