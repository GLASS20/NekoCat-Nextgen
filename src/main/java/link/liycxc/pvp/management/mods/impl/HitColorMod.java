package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventHitOverlay;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

import java.awt.*;

public class HitColorMod extends Mod {

	public HitColorMod() {
		super("HitColor", "Change the color of the hit", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addSliderSetting("Alpha", this, 0.8, 0, 1, false);
	}
	
	@EventTarget
	public void onHitOverlay(EventHitOverlay event) {
		Color color = ColorUtils.getClientColor(0);
		float alpha = (float) NekoCat.instance.settingsManager.getSettingByName(this, "Alpha").getValDouble();
		
		event.setRed(color.getRed() / 255F);
		event.setGreen(color.getGreen() / 255F);
		event.setBlue(color.getBlue() / 255F);
		event.setAlpha(alpha);
	}
}
