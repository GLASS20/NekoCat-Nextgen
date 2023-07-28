package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.HudMod;

public class WeatherDisplayMod extends HudMod{

	public WeatherDisplayMod() {
		super("Weather Display", "Display current weather");
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
		
		String weather = "Clear";
		
		if(mc.theWorld.isRaining()) {
			weather = "Raining";
		}
		
		if(mc.theWorld.isThundering()) {
			weather = "Thundering";
		}
		
		return "Weather: " + weather;
	}
}
