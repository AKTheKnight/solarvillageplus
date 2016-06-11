package com.aktheknight.solarvillageplus.blocks.tiles;

import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntitySolarPanel extends TileEntity implements ITickable {
    
    private final SolarPanelContainer container;
    
    public TileEntitySolarPanel(long capacity, int gen) {
        this.container = new SolarPanelContainer(capacity, gen);
    }
    
    @Override
    public void update () {
        if (this.hasWorldObj() && !this.worldObj.provider.getHasNoSky() && this.worldObj.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.worldObj.isRaining() && this.worldObj.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
            this.container.generatePower();
    }
    
    @Override
    public void readFromNBT (NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.container.capacity = compound.getLong("capacity");
        this.container.gen = compound.getInteger("gen");
        this.container.setPower(compound.getLong("storedPower"));
    }
    
    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {
        compound.setLong("capacity", this.container.capacity);
        compound.setInteger("gen", this.container.gen);
        compound.setLong("StoredPower", this.container.getStoredPower());
        return super.writeToNBT(compound);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability (Capability<T> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return (T) this.container;
            
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return true;
            
        return super.hasCapability(capability, facing);
    }
}