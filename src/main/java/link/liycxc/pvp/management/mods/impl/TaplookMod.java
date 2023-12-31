package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventTick;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;

import java.util.ArrayList;

public class TaplookMod extends Mod {

	private boolean active;
	private int prevPerspective;
	
	public TaplookMod() {
		super("Taplook", "Switch viewing orientation at the touch of a button", ModCategory.PLAYER);
	}

	@Override
	public void setup() {
		
		ArrayList<String> options = new ArrayList<String>();
		
		options.add("Front");
		options.add("Behind");
		
		this.addModeSetting("Perspective", this, "Front", options);
	}
	
	@EventTarget
	public void onTick(EventTick event) {
		if(NekoCat.instance.keyBindManager.TAPLOOK.isKeyDown()) {
			if(!active) {
				this.start();
			}
		}else if(active) {
			this.stop();
		}
	}
	
	private void start() {
		
		String type = NekoCat.instance.settingsManager.getSettingByName(this, "Perspective").getValString();
		int perspective = 0;
		
		active = true;
		prevPerspective = mc.gameSettings.thirdPersonView;
		
		switch(type) {
			case "Front":
				perspective = 2;
				break;
			case "Behind":
				perspective = 1;
				break;
		}
		
		mc.gameSettings.thirdPersonView = perspective;
		mc.renderGlobal.setDisplayListEntitiesDirty();
	}
	
	private void stop() {
		active = false;
		mc.gameSettings.thirdPersonView = prevPerspective;
		mc.renderGlobal.setDisplayListEntitiesDirty();
	}
}
