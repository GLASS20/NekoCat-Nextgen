package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.impl.EventText;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class NameProtectMod extends Mod {

	public NameProtectMod() {
		super("Name Protect", "Protect your name", ModCategory.RENDER);
	}

	@EventTarget
	public void onText(EventText event) {
		event.replace(mc.getSession().getUsername(), "You");
	}
}
