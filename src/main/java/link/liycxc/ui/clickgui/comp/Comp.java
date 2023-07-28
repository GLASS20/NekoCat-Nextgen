package link.liycxc.ui.clickgui.comp;

import link.liycxc.api.value.Value;
import link.liycxc.ui.clickgui.impl.FeatureCategory;
import link.liycxc.modules.Module;

public class Comp {

    public double x, y, width, height;
    public FeatureCategory parent;
    public Module mod;
    public Value setting;

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {}

    public void mouseReleased(int mouseX, int mouseY, int state) {}

    public void drawScreen(int mouseX, int mouseY) {}

    public void keyTyped(char typedChar, int keyCode) {}
}