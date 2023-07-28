package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventKey;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.api.events.impl.EventTick;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.pvp.management.mods.HudMod;
import link.liycxc.utils.TimerUtils;

import java.text.DecimalFormat;

public class StopwatchMod extends HudMod{

	private TimerUtils timer = new TimerUtils();
	private int pressCount;
	private float currentTime;
	private DecimalFormat timeFormat = new DecimalFormat("0.00");
	
	public StopwatchMod() {
		super("Stopwatch", "Measure the time");
	}

	@Override
	public void onEnable() {
		super.onEnable();
		if(timer != null) {
			timer.reset();
		}
		pressCount = 0;
		currentTime = 0;
	}
	
	@EventTarget
	public void onTick(EventTick event) {
		switch(pressCount) {
			case 0:
				timer.reset();
				break;
			case 1:
				currentTime = (timer.getElapsedTime() / 1000F);
				break;
			case 3:
				timer.reset();
				currentTime = 0;
				pressCount = 0;
				break;
		}
	}
	
	@EventTarget
	public void onKey(EventKey event) {
		if(NekoCat.instance.keyBindManager.STOPWATCH.isKeyDown()) {
			pressCount++;
		}
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
		return timeFormat.format(currentTime) + " s";
	}
}
