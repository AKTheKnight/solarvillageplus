package com.aktheknight.solarvillageplus.blocks.tiles;

import com.aktheknight.solarvillageplus.integrations.waila.IWailaBodyMessage;
import com.aktheknight.solarvillageplus.util.LanguageHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

public class TileEntitySolarPanel extends TileEntity implements ITickable,IWailaBodyMessage {
    
    private SolarPanelContainer container;
    
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
        this.container = new SolarPanelContainer(compound.getCompoundTag("TeslaContainer"));
    }
    
    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) {
        compound.setTag("TeslaContainer",this.container.serializeNBT());
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
    public List<String> getWailaBodyToolTip(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        int percent = (int) ((container.getStoredPower() * 100 / container.getCapacity()));
        currentTip.add(String.format("%s: §e%d§8/§e%d§7 (§e%d%%§7)",
                LanguageHelper.LABEL.translateMessage("energy_fill"),
                container.getStoredPower(),
                container.getCapacity(),
                Math.round(percent)
        ));
        return currentTip;
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return true;
            
        return super.hasCapability(capability, facing);
    }
}