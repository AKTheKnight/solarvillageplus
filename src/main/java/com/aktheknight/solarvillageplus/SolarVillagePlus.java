package com.aktheknight.solarvillageplus;

import com.aktheknight.solarvillageplus.common.CommonProxy;
import com.aktheknight.solarvillageplus.util.SolarvillageplusCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid= SolarVillagePlus.MODID, name= SolarVillagePlus.MODNAME, version= SolarVillagePlus.VERSION)

public class SolarVillagePlus {

	public static final String MODID = "solarvillageplus";
	static final String MODNAME = "SolarVillagePlus";
	static final String VERSION = "@VERSION@";

    public static boolean tesla = false;
	
	@Mod.Instance
	public static SolarVillagePlus instance;

	public static CreativeTabs solarvillageplusTab = new SolarvillageplusCreativeTab(CreativeTabs.getNextID(), "solarvillageplus");
	
	static Logger LOGGER = LogManager.getLogger(MODID);
	
	@SidedProxy(clientSide="com.aktheknight.solarvillageplus.ClientProxy", serverSide="com.aktheknight.solarvillageplus.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Pre-Init");
		proxy.preInit(event);
		LOGGER.log(Level.INFO, "Finished Pre-Init");
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Init");
	    proxy.init(event);
	    LOGGER.log(Level.INFO, "Finished Init");
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOGGER.log(Level.INFO, "Starting Post Init");
	    proxy.postInit(event);
	    LOGGER.log(Level.INFO, "Finished Post Init");
        if (Loader.isModLoaded("Tesla"))
            tesla = true;
	}

}
