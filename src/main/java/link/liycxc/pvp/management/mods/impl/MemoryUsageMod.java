package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.pvp.management.mods.HudMod;

public class MemoryUsageMod extends HudMod{

	public MemoryUsageMod() {
		super("Memory Usage", "Display Minecraft memory usage");
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
		
		Runtime runtime = Runtime.getRuntime();
		String mem = "Mem: " + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "%";
		
		return mem;
	}
}
