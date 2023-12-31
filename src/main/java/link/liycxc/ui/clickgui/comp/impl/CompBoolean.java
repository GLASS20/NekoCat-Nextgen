package link.liycxc.ui.clickgui.comp.impl;


import link.liycxc.api.value.impl.BoolValue;
import link.liycxc.ui.clickgui.impl.FeatureCategory;
import link.liycxc.ui.clickgui.impl.features.CombatModules;
import link.liycxc.ui.clickgui.impl.features.MovementModules;
import link.liycxc.ui.clickgui.impl.features.RenderModules;
import link.liycxc.ui.clickgui.impl.features.UtiltyModules;
import link.liycxc.utils.animation.simple.SimpleAnimation;
import link.liycxc.utils.mouse.MouseUtils;
import link.liycxc.NekoCat;
import link.liycxc.modules.Module;
import link.liycxc.ui.clickgui.comp.Comp;
import link.liycxc.utils.GlUtils;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.utils.font.FontUtils;
import link.liycxc.utils.render.RoundedUtils;

import java.awt.*;

public class CompBoolean extends Comp {

	private SimpleAnimation animation = new SimpleAnimation(0.0F);
	private SimpleAnimation animation2 = new SimpleAnimation(0.0F);
	
    public CompBoolean(double x, double y, FeatureCategory parent, Module mod, BoolValue setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
    	animation.setAnimation((boolean) setting.get() ? 1 : 0, 10);
    	animation2.setAnimation((boolean) setting.get() ? 255 : 0, 12);
    	
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getBackgroundColor(2));
    	
    	GlUtils.startScale((float) (parent.getX() + x - 70 + parent.getX() + x - 70 + 10) / 2, (float) (parent.getY() + y + parent.getY() + y + 10) / 2, animation.getValue());
    	RoundedUtils.drawRound((float) (parent.getX() + x - 70), (float) (parent.getY() + y), 10, 10, 3, ColorUtils.getClientColor(0, (int) animation2.getValue()));
        FontUtils.icon20.drawString("H", (float) (parent.getX() + x - 70), (float) (parent.getY() + y + 3), new Color(255, 255, 255).getRGB());
        GlUtils.stopScale();

        FontUtils.regular20.drawString(setting.getName(), (float) (parent.getX() + x - 55), (float) (parent.getY() + y + 2), ColorUtils.getFontColor(2).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        float ad = 0;
        if(NekoCat.instance.guiManager.getgClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getgClickGUI().categoryManager.getCategoryByClass(FeatureCategory.class))) {
            ad = FeatureCategory.scrollVAnimation.getValue();
        }
        if(NekoCat.instance.guiManager.getgClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getgClickGUI().categoryManager.getCategoryByClass(CombatModules.class))) {
            ad = CombatModules.scrollVAnimation.getValue();
        }
        if(NekoCat.instance.guiManager.getgClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getgClickGUI().categoryManager.getCategoryByClass(MovementModules.class))) {
            ad = MovementModules.scrollVAnimation.getValue();
        }
        if(NekoCat.instance.guiManager.getgClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getgClickGUI().categoryManager.getCategoryByClass(RenderModules.class))) {
            ad = RenderModules.scrollVAnimation.getValue();
        }
        if(NekoCat.instance.guiManager.getgClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getgClickGUI().categoryManager.getCategoryByClass(UtiltyModules.class))) {
            ad = UtiltyModules.scrollVAnimation.getValue();
        }
        if(MouseUtils.isInside(mouseX, mouseY, parent.getX() + x - 70, parent.getY() + y + ad, 10, 10)) {
            if (mouseButton == 0) {
                setting.set(!(boolean)setting.get());
                parent.isHitd = true;
            } else if (mouseButton == 1) {
                setting.set(setting.getDefaultVal());
                parent.isHitd = true;
            }
        }
    }
}
