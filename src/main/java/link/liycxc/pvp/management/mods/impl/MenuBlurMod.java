package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.pvp.management.settings.Setting;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class MenuBlurMod extends Mod {

	public MenuBlurMod() {
		super("Menu Blur", "Blur the menu", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		NekoCat.instance.settingsManager.addSetting(new Setting("Radius", this, 20, 1, 40, true));
	}
}
