package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.HudMod;
import net.minecraft.client.Minecraft;

public class FPSDisplayMod extends HudMod{

	public FPSDisplayMod() {
		super("FPS Display", "Display your Minecraft FPS");
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
		return Minecraft.getDebugFPS() + " FPS";
	}
}
