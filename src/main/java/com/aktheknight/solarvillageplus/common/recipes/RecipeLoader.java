package com.aktheknight.solarvillageplus.common.recipes;

import com.aktheknight.solarvillageplus.common.CommonProxy;
import com.aktheknight.solarvillageplus.util.PanelTier;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
public class RecipeLoader {
	public static void init() {
		GameRegistry.addRecipe(new ShapedOreRecipe(getPanel(PanelTier.tier1),
						"   ",
						"qaq",
						"gpg",
						'q', "ingotGold",
						'g', "ingotIron",
						'p', net.minecraft.init.Blocks.DAYLIGHT_DETECTOR,
						'a', "dustRedstone"
				)
		);
		GameRegistry.addRecipe(new ShapedOreRecipe(getPanel(PanelTier.tier2),
						"   ",
						"qaq",
						"gpg",
						'q', "gemDiamond",
						'g', "ingotIron",
						'p', getPanel(PanelTier.tier1),
						'a', "panelGlass"
				)
		);
		if(Items.tinIngot.isLoaded()) {
			GameRegistry.addRecipe(new ShapedOreRecipe(getPanel(PanelTier.tier3),
							"   ",
							"cac",
							"gpg",
							'g', "ingotTin",
							'p', getPanel(PanelTier.tier2),
							'a', "panelGlass",
							'c', net.minecraft.init.Items.EMERALD
					)
			);
		}
	}
	public static Block getPanel(PanelTier tier) {
		return Block.getBlockFromName("solarvillageplus:panel_"+tier.toString());
	}
}
