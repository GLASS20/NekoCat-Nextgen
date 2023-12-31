package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventKey;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class ClickGUIMod extends Mod {

	public ClickGUIMod() {
		super("ClickGUI", "Show client settings", ModCategory.OTHER);
	}

	@Override
	public void setup() {
		this.setHide(true);
		this.setToggled(true);
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(event.getKey() == NekoCat.instance.keyBindManager.CLICKGUI.getKeyCode()) {
	    	mc.displayGuiScreen(NekoCat.instance.guiManager.getClickGUI());
		}
		if(event.getKey() == NekoCat.instance.keyBindManager.HACKCLICKGUI.getKeyCode()) {
			mc.displayGuiScreen(NekoCat.instance.guiManager.getgClickGUI());
		}
	}
	
    @Override
    public void onDisable() {
        super.onEnable();
        this.setToggled(true);
    }
}
