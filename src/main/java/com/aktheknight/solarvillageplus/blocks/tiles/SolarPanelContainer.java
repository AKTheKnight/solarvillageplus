package com.aktheknight.solarvillageplus.blocks.tiles;

import net.darkhax.tesla.api.BaseTeslaContainer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class SolarPanelContainer implements ITeslaHolder,ITeslaProducer,INBTSerializable<NBTTagCompound> {

    public SolarPanelContainer(long capacity, int gen) {
        this.capacity = capacity;
        this.gen = gen;
    }

    public SolarPanelContainer(NBTTagCompound tag) {
        this.deserializeNBT(tag);
    }

    //Power in panel
    protected long storedPower = 0;
    //Max power in panel
    protected long capacity;
    //Amount to gen per tick
    protected int gen;

    @Override
    public long takePower(long power, boolean simulated) {
        long remove = Math.min(Math.min(this.storedPower, this.gen * 2), power);

        if (!simulated)
            this.storedPower -= remove;

        return remove;
    }

    @Override
    public long getStoredPower() {
        return this.storedPower;
    }

    @Override
    public long getCapacity() {
        return this.capacity;
    }

    public int getGen() {
        return this.gen;
    }

    public void generatePower () {
        this.storedPower += this.gen;

        if (this.storedPower > this.getCapacity())
            this.storedPower = this.getCapacity();
    }


    @Override
    public NBTTagCompound serializeNBT () {

        final NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setLong("TeslaPower", this.storedPower);
        dataTag.setLong("TeslaCapacity", this.capacity);

        return dataTag;
    }

    @Override
    public void deserializeNBT (NBTTagCompound nbt) {

        this.storedPower = nbt.getLong("TeslaPower");

        if (nbt.hasKey("TeslaCapacity"))
            this.capacity = nbt.getLong("TeslaCapacity");

        if (this.storedPower > this.capacity)
            this.storedPower = this.capacity;
    }
    //Set power (DO NOT USE EXCEPT IN EMERGENCY)
    //JK
    protected void setPower (long power) {
        this.storedPower = power;
    }
}
