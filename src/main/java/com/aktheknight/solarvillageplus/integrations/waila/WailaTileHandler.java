package com.aktheknight.solarvillageplus.integrations.waila;

import com.aktheknight.solarvillageplus.blocks.SolarPanel;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class WailaTileHandler implements IWailaDataProvider {

    public static void callbackRegister(IWailaRegistrar registrar) {
        WailaTileHandler instance = new WailaTileHandler();

        registrar.registerHeadProvider(instance, SolarPanel.class);
        registrar.registerBodyProvider(instance, SolarPanel.class);
        registrar.registerTailProvider(instance, SolarPanel.class);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return accessor.getStack();
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (tileEntity instanceof IWailaHeadMessage)
            return ((IWailaHeadMessage) tileEntity).getWailaHeadToolTip(itemStack, currenttip, accessor, config);

        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (tileEntity instanceof IWailaBodyMessage)
            return ((IWailaBodyMessage) tileEntity).getWailaBodyToolTip(itemStack, currenttip, accessor, config);

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (tileEntity instanceof IWailaTailMessage)
            return ((IWailaTailMessage) tileEntity).getWailaTailToolTip(itemStack, currenttip, accessor, config);

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
        if (te != null)
            te.writeToNBT(tag);

        return tag;
    }
}
