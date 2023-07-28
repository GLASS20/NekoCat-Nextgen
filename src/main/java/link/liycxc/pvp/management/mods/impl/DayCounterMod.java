package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.pvp.management.mods.HudMod;

public class DayCounterMod extends HudMod{

	public DayCounterMod() {
		super("Day Counter", "Count Minecraft day");
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
		
		if(mc.theWorld != null && mc.theWorld.getWorldInfo() != null) {
			long time = mc.theWorld.getWorldInfo().getWorldTotalTime() / 24000L;
			
			return time + " Day" + (time != 1L ? "s" :"");
		}else {
			return null;
		}
	}
}
