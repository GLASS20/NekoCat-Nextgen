package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.HudMod;

public class SaturationMod extends HudMod{

	public SaturationMod() {
		super("Saturation", "Display player saturation");
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
		return String.format("%.0f", mc.thePlayer.getFoodStats().getSaturationLevel());
	}
}
