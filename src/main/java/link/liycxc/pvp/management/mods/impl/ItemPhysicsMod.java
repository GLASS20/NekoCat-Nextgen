package link.liycxc.pvp.management.mods.impl;

import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

public class ItemPhysicsMod extends Mod {

	public ItemPhysicsMod() {
		super("Item Physics", "Add physics to the item", ModCategory.RENDER);
	}

	
	@Override
	public void setup() {
		this.addSliderSetting("Speed", this, 1, 0.5, 4, false);
	}
}
