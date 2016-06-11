package com.aktheknight.solarvillageplus.integrations;

import com.aktheknight.solarvillageplus.IntegrationModIDs;
import com.aktheknight.solarvillageplus.SolarVillagePlus;
import com.aktheknight.solarvillageplus.integrations.waila.Waila;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationsManager {
    private static IntegrationsManager INSTANCE = new IntegrationsManager();
    private final List<IIntegration> integrationMods = new ArrayList<IIntegration>();

    public static IntegrationsManager instance() {
        return INSTANCE;
    }

    public void index() {
        Map<String, Class<? extends IIntegration>> integrationClasses = new HashMap<String, Class<? extends IIntegration>>();

        try {
            integrationClasses.put(IntegrationModIDs.WAILA, Waila.class);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        // hmm... configuration stuff?

        for (Map.Entry<String, Class<? extends IIntegration>> entry : integrationClasses.entrySet()) {
            if (Loader.isModLoaded(entry.getKey())) {
                try {
                    integrationMods.add(entry.getValue().newInstance());
                    SolarVillagePlus.proxy.LOGGER.info("Integration with " + entry.getKey() + ": Enabled");
                } catch (Throwable ex) {
                    SolarVillagePlus.proxy.LOGGER.error("Failed to load integration correctly");
                    ex.printStackTrace();
                }
            } else {
                SolarVillagePlus.proxy.LOGGER.info("Integration with " + entry.getKey() + ": Disabled");
            }
        }
    }

    public void preInit() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.preInit();
            } catch (Throwable ex) {
                SolarVillagePlus.proxy.LOGGER.error("(Pre Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }

    public void init() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.init();
            } catch (Throwable ex) {
                SolarVillagePlus.proxy.LOGGER.error("(Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }

    public void postInit() {
        for (IIntegration integration : integrationMods) {
            try {
                integration.postInit();
            } catch (Throwable ex) {
                SolarVillagePlus.proxy.LOGGER.error("(Post Init) Unable to load integration from " + integration.getClass());
                ex.printStackTrace();
            }
        }
    }
}
