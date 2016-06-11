package com.aktheknight.solarvillageplus.blocks.tiles;

import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;

public class SolarPanelContainer implements ITeslaHolder, ITeslaProducer {

    public SolarPanelContainer(long capacity, int gen) {
        this.capacity = capacity;
        this.gen = gen;
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

    //Set power (DO NOT USE EXCEPT IN EMERGENCY)
    //JK
    protected void setPower (long power) {
        this.storedPower = power;
    }
}
