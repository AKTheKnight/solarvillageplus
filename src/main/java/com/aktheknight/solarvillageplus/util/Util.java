package com.aktheknight.solarvillageplus.util;

import com.aktheknight.solarvillageplus.SolarVillagePlus;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	
	public static Logger LOGGER = LogManager.getLogger(SolarVillagePlus.MODID);
	
	public static void error(EntityPlayer player, String msg) {
        player.addChatComponentMessage(new TextComponentString(TextFormatting.RED + msg));
	}

    public static void error(World world, EntityPlayer player, String msg) {
        if (world.isRemote) {
            player.addChatComponentMessage(new TextComponentString(TextFormatting.RED + msg));
        }
    }
	
	public static void notify(EntityPlayer player, String msg) {
        player.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + msg));
	}

    public static void notify(World world, EntityPlayer player, String msg) {
        if (world.isRemote) {
            player.addChatComponentMessage(new TextComponentString(TextFormatting.GREEN + msg));
        }
    }
	
	public static String getBlockName(Block block, int meta) {
        ItemStack stack = new ItemStack(block, 1, meta);
        if (stack.getItem() == null) {
            return null;
        }
        return stack.getDisplayName();
    }
	
	public static NBTTagCompound getTags(ItemStack stack) {
        NBTTagCompound tagComp = stack.getTagCompound();
        if (tagComp == null){
            tagComp = new NBTTagCompound();
            stack.setTagCompound(tagComp);
        }
        return tagComp;
    }
}
