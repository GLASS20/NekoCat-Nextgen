package link.liycxc.pvp.management.mods.impl;

import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.utils.server.ServerUtils;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.pvp.management.mods.HudMod;

public class ServerIPDisplayMod extends HudMod{

	public ServerIPDisplayMod() {
		super("ServerIP Display", "Display server ip you have joined");
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
		return ServerUtils.getServerIP();
	}
}
