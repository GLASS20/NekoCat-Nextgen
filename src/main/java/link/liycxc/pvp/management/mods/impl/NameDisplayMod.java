package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.pvp.management.mods.HudMod;

import java.util.ArrayList;

public class NameDisplayMod extends HudMod{

	public NameDisplayMod() {
		super("Name Display", "Display your Minecraft account name");
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Name");
		options.add("IGN");
		
		this.addModeSetting("Type", this, "IGN", options);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		super.onRender2D();
	}
	
	@EventTarget
	public void onRenderShadow(EventRenderShadow event) {
		super.onRenderShadow();
	}
	
	@Override
	public String getText() {
		
		String mode = NekoCat.instance.settingsManager.getSettingByName(this, "Type").getValString();
		
		return mode + ": " + mc.getSession().getUsername();
	}
}
