package link.liycxc.pvp.management.mods.impl;

import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;
import link.liycxc.utils.ClientUtils;

public class ForgeSpooferMod extends Mod {

	public ForgeSpooferMod() {
		super("Fake Forge", "Recognize it as Forge on the server side", ModCategory.OTHER);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		ClientUtils.showNotification("Mod", "Please reconnect to the server to reflect");
	}
}
