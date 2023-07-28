package link.liycxc.pvp.management.mods.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.api.events.impl.EventRender3D;
import link.liycxc.api.events.EventTarget;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;
import link.liycxc.utils.render.RenderUtils;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbsMod extends Mod {

    private List<Vec3> path = new ArrayList<>();
    
	public BreadcrumbsMod() {
		super("Breadcrumbs", "Render the trail you walked on", ModCategory.RENDER);
	}

	@Override
	public void setup() {
		this.addBooleanSetting("Timeout", this, true);
		this.addSliderSetting("Time", this, 15, 1, 150, true);
	}

	@EventTarget
	public void onRender3D(EventRender3D event) {
		RenderUtils.renderBreadCrumbs(path);
	}
	
	@EventTarget
	public void onPreMotionUpdate(EventPreMotion event) {
        if (mc.thePlayer.lastTickPosX != mc.thePlayer.posX || mc.thePlayer.lastTickPosY != mc.thePlayer.posY || mc.thePlayer.lastTickPosZ != mc.thePlayer.posZ) {
            path.add(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
        }
        
        if (NekoCat.instance.settingsManager.getSettingByName(this, "Timeout").getValBoolean()) {
            while (path.size() > (int) NekoCat.instance.settingsManager.getSettingByName(this, "Time").getValDouble()) {
                path.remove(0);
            }
        }
	}
}
