package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.pvp.management.settings.Setting;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

import java.awt.*;

public class GlintColorMod extends Mod {

	public static GlintColorMod instance = new GlintColorMod();
	
	public GlintColorMod() {
		super("Glint Color", "Customize Glint Color", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		NekoCat.instance.settingsManager.addSetting(new Setting("Rainbow", this, false));
	}
	
	public Color getColor() {
		if(NekoCat.instance.settingsManager.getSettingByClass(GlintColorMod.class, "Rainbow").getValBoolean()) {
			return ColorUtils.rainbow(0, 25, 255);
		}
		
		return ColorUtils.getClientColor(0);
	}
}
