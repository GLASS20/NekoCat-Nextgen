package link.liycxc.api.events.impl;

import link.liycxc.api.events.Event;

public class EventScrollMouse extends Event{

	private int amount;
	
	public EventScrollMouse(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}
}
