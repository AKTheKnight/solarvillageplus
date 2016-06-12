package com.aktheknight.solarvillageplus.blocks.tiles;

import net.darkhax.tesla.api.BaseTeslaContainer;
import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class SolarPanelContainer extends BaseTeslaContainer {

    public SolarPanelContainer() {
        this(5000,50,50);
    }
    public SolarPanelContainer(long capacity, long input, long output) {
        this(0,capacity, input, output);
    }

    public SolarPanelContainer(long power, long capacity, long input, long output) {
        super(power, capacity, input, output);
    }

    public SolarPanelContainer(NBTTagCompound dataTag) {
        this.deserializeNBT(dataTag);
    }
}
