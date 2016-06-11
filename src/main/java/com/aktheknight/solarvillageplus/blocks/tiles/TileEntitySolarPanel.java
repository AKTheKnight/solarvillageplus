package com.aktheknight.solarvillageplus.blocks.tiles;

import com.aktheknight.solarvillageplus.integrations.waila.IWailaBodyMessage;
import com.aktheknight.solarvillageplus.util.LanguageHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.darkhax.tesla.capability.TeslaCapabilities;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntitySolarPanel extends TileEntity implements ITickable,IWailaBodyMessage {
    
    private SolarPanelContainer container;
    private int renderedFragment = 0;
    
    public TileEntitySolarPanel(long capacity, int gen) {
        this.container = new SolarPanelContainer(capacity, gen);
    }

    @Override
    public void update () {
        if (this.hasWorldObj() && !this.worldObj.provider.getHasNoSky() && this.worldObj.canBlockSeeSky(this.pos.offset(EnumFacing.UP)) && !this.worldObj.isRaining() && this.worldObj.getSkylightSubtracted() == 0 && this.container.getStoredPower() != this.container.getCapacity())
            this.container.generatePower();
        TileEntity tileCheck = worldObj.getTileEntity(this.pos.down());
        if(tileCheck!=null&& tileCheck.hasCapability(TeslaCapabilities.CAPABILITY_CONSUMER,EnumFacing.UP))
            container.takePower(tileCheck.getCapability(TeslaCapabilities.CAPABILITY_CONSUMER,EnumFacing.UP).givePower(container.getGen()*2,false),false);
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
        currentTip.add(String.format("%s: §e%dT§7/tick",
                LanguageHelper.LABEL.translateMessage("generate"),
                this.container.getGen()
        ));
        return currentTip;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound data = new NBTTagCompound();
        writeToNBT(data);
        return new SPacketUpdateTileEntity(this.pos, 1, data);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }
    public void markForUpdate() {
        if (this.renderedFragment > 0) {
            this.renderedFragment |= 0x1;
        } else if (this.worldObj != null) {
            Block block = worldObj.getBlockState(this.pos).getBlock();
            this.worldObj.notifyBlockUpdate(this.pos, worldObj.getBlockState(this.pos), worldObj.getBlockState(this.pos), 3);

            int xCoord = this.pos.getX();
            int yCoord = this.pos.getY();
            int zCoord = this.pos.getZ();

            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord + 1, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord - 1, yCoord, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord + 1, yCoord, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord - 1), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord + 1), block);
        }
    }
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
    }

    public void markForLightUpdate() {
        if (this.worldObj.isRemote) {
            this.worldObj.notifyBlockUpdate(this.pos, worldObj.getBlockState(this.pos), worldObj.getBlockState(this.pos), 3);
        }

        this.worldObj.checkLightFor(EnumSkyBlock.BLOCK, this.pos);
    }

    @Override
    public void onLoad() {
        if (this.isInvalid())
            this.validate();

        markForUpdate();
    }

    @Override
    public void onChunkUnload() {
        if (!this.isInvalid())
            this.invalidate();
    }


    @Override
    public void onDataPacket(NetworkManager networkManager, SPacketUpdateTileEntity s35PacketUpdateTileEntity) {
        readFromNBT(s35PacketUpdateTileEntity.getNbtCompound());
        worldObj.markBlockRangeForRenderUpdate(this.pos, this.pos);
        markForUpdate();
    }

    @Override
    public boolean hasCapability (Capability<?> capability, EnumFacing facing) {
        
        if (facing == EnumFacing.DOWN && (capability == TeslaCapabilities.CAPABILITY_PRODUCER || capability == TeslaCapabilities.CAPABILITY_HOLDER))
            return true;
            
        return super.hasCapability(capability, facing);
    }
}