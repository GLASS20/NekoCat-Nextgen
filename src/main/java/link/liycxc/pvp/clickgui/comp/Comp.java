package link.liycxc.pvp.clickgui.comp;

import link.liycxc.pvp.clickgui.category.impl.FeatureCategory;
import link.liycxc.pvp.management.settings.Setting;
import link.liycxc.pvp.management.mods.Mod;

public class Comp {

    public float x, y, width, height;
    public FeatureCategory parent;
    public Mod mod;
    public Setting setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int state) {}

    public void drawScreen(int mouseX, int mouseY) {}

    public void keyTyped(char typedChar, int keyCode) {}

}
