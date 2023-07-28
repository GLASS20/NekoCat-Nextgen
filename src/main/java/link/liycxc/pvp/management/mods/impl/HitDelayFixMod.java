package link.liycxc.pvp.management.mods.impl;

import link.liycxc.utils.ClientUtils;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class HitDelayFixMod extends Mod {

	public HitDelayFixMod() {
		super("Hit Delay Fix", "Fix delay which is triggered every time you click without attacking a player mob", ModCategory.PLAYER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		ClientUtils.showNotification("Warning", "This mod is banned on some servers");
	}
}
