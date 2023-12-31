package link.liycxc.pvp.clickgui.category.impl;

import link.liycxc.pvp.GuiEditHUD;
import link.liycxc.utils.animation.normal.Direction;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.utils.mouse.MouseUtils;
import link.liycxc.NekoCat;
import link.liycxc.pvp.clickgui.category.Category;
import link.liycxc.utils.font.FontUtils;
import link.liycxc.utils.render.RoundedUtils;

public class EditHUDCategory extends Category {

	private boolean edithud;
	
	public EditHUDCategory() {
		super("Edit HUD");
	}
	
	@Override
	public void initGui() {
		edithud = false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	FontUtils.regular24.drawString("Can move the HUD on the screen", this.getX() + 95, this.getY() + 12, ColorUtils.getFontColor(1).getRGB());
    	
    	RoundedUtils.drawRound((float) this.getX() + 95, (float) this.getY() + 30, (float) 200, (float) 90, 6, ColorUtils.getBackgroundColor(4));
    	FontUtils.regular24.drawCenteredString("Click this to move", (float) (this.getX() + 95 + this.getX() + 95 + 200) / 2 - 1, (float) (this.getY() + 30 + this.getY() + 30 + 90) / 2 - 5, ColorUtils.getFontColor(2).getRGB());
    	
    	if(NekoCat.instance.guiManager.getClickGUI().introAnimation.isDone(Direction.BACKWARDS) && edithud == true) {
        	mc.displayGuiScreen(new GuiEditHUD(true));
    	}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(NekoCat.instance.guiManager.getClickGUI().selectedCategory.equals(NekoCat.instance.guiManager.getClickGUI().categoryManager.getCategoryByClass(EditHUDCategory.class)) && MouseUtils.isInside(mouseX, mouseY, this.getX() + 95, this.getY() + 30, 200, 90) && mouseButton == 0) {
        	NekoCat.instance.guiManager.getClickGUI().introAnimation.setDirection(Direction.BACKWARDS);
        	edithud = true;
        }
	}
}
