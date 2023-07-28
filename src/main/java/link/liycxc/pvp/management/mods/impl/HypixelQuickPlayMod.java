package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventKey;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class HypixelQuickPlayMod extends Mod {

	public HypixelQuickPlayMod() {
		super("Hypixel Quick Play", "Quickly enter Hypixel commands", ModCategory.PLAYER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.QUICKPLAY.getKeyCode()) {
	    	mc.displayGuiScreen(NekoCat.instance.guiManager.getGuiQuickPlay());
		}
	}
}
