package link.liycxc.api.events.impl;

import link.liycxc.api.events.Event;

public class EventRenderShadow extends Event{

	private float partialTicks;
	
	public EventRenderShadow(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}
