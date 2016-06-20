package com.aktheknight.solarvillageplus.common.config;

import com.aktheknight.solarvillageplus.util.ConfigHelper;
import com.aktheknight.solarvillageplus.util.PanelTier;
import net.minecraftforge.common.config.Configuration;

import java.util.HashMap;
import java.util.Map;

/* You are free to:
 * 
 * Share — copy and redistribute the material in any medium or format
 * Adapt — remix, transform, and build upon the material
 * for any purpose, even commercially.
 * The licensor cannot revoke these freedoms as long as you follow the license terms.
 * Under the following terms:

 * Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
 * ShareAlike — If you remix, transform, or build upon the material, you must distribute your contributions under the same license as the original.
 * No additional restrictions — You may not apply legal terms or technological measures that legally restrict others from doing anything the license permits.
 * Notices:
 * 
 * You do not have to comply with the license for elements of the material in the public domain or where your use is permitted by an applicable exception or limitation.
 * No warranties are given. The license may not give you all of the permissions necessary for your intended use. For example, other rights such as publicity, privacy, or moral rights may limit how you use the material.
 */
public class ConfigPanels {
	public static final Map<PanelTier, PanelConfig> Panels = new HashMap<PanelTier, PanelConfig>();
	public static final Map<PanelTier, PanelConfig> PanelsDefaults = new HashMap<PanelTier, PanelConfig>();
	static {
		for (PanelTier tier : PanelTier.values()) {
			PanelConfig defaultConfig = new PanelConfig();
			defaultConfig.Enabled = true;
			switch (tier) {
				default:
					defaultConfig.Enabled = true;
					break;
				case tier1:
					defaultConfig.Enabled = true;
					break;
				case tier2:
					defaultConfig.Enabled = true;
					break;
				case tier3:
					defaultConfig.Enabled = true;
					break;
				case tier4:
					defaultConfig.Enabled = true;
					break;
				case tier5:
					defaultConfig.Enabled = false;
					break;
				case tier6:
					defaultConfig.Enabled = false;
					break;
			}
			PanelsDefaults.put(tier,defaultConfig);
		}
	}

	public static void init(Configuration configuration) {
		final String DESC_ENABLED = "Enable %s panel";

		for (PanelTier tier : PanelTier.values()) {
			PanelConfig panelConfig;
			PanelConfig defaultConfig = PanelsDefaults.get(tier);
			if(Panels.containsKey(tier)) {
				panelConfig = Panels.get(tier);
			}
			else {
				panelConfig = new PanelConfig();
			}

			panelConfig.Enabled = ConfigHelper.getBoolean(configuration,tier.toString(),Config.CONFIG_PANELS,defaultConfig.Enabled,String.format(DESC_ENABLED,tier.toString()));
		}
	}

	public static class PanelConfig {
		public boolean Enabled;

	}
}
