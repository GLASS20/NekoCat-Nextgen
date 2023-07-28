package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventKey;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class ScreenshotViewerMod extends Mod {

	public ScreenshotViewerMod() {
		super("Screenshot Viewer", "View a list of screenshots", ModCategory.RENDER);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.SCREENSHOT_VIEWER.getKeyCode()) {
			mc.displayGuiScreen(NekoCat.instance.guiManager.getGuiScreenshotViewer());
		}
	}
}
