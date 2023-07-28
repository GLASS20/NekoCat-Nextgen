package link.liycxc.pvp.clickgui.category.impl;

import link.liycxc.pvp.GuiTransparentField;
import link.liycxc.utils.animation.simple.SimpleAnimation;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.utils.mouse.MouseUtils;
import link.liycxc.NekoCat;
import link.liycxc.pvp.clickgui.category.Category;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.utils.ClientUtils;
import link.liycxc.utils.font.FontUtils;
import link.liycxc.utils.render.RoundedUtils;

import java.awt.*;
import java.io.File;

public class ConfigCategory extends Category {

	private GuiTransparentField configNameField;
    private SimpleAnimation scrollAnimation = new SimpleAnimation(0.0F);
    private boolean canToggle;
    
    private File removeFile;
    private boolean removed;
    
	public ConfigCategory() {
		super("Config");
	}

	@Override
	public void initGui() {
		configNameField = new GuiTransparentField(1, mc.fontRendererObj, (int) this.getX() + 95, (int) this.getY() + 207, 135, 20, ColorUtils.getFontColor(1).getRGB());
		canToggle = false;
		removed = true;
		removeFile = null;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		int offsetY = 15;
		
		NekoCat.instance.configManager.loadConfigs();
		
    	//Draw config list
    	for(File f : NekoCat.instance.configManager.getConfigs()) {
    		
    		String configName = f.getName();
    		int MAX_CHAR = 34;
    		int maxLength = (configName.length() < MAX_CHAR) ? configName.length() : MAX_CHAR;
    		configName = configName.substring(0, maxLength).replace(".txt", "");
			
    		RoundedUtils.drawRound((float) this.getX() + 95, (float) (this.getY() + offsetY + scrollAnimation.getValue()), 200, 26, 8, ColorUtils.getBackgroundColor(4));
    		FontUtils.regular20.drawString(configName, this.getX() + 105, this.getY() + offsetY + 11 + scrollAnimation.getValue(), ColorUtils.getFontColor(2).getRGB());

            FontUtils.icon20.drawString("M", this.getX() + 275, this.getY() + offsetY + 11, new Color(255, 20, 20).getRGB());
    		offsetY+= 35;
    	}
    	
		//Under bar
    	RoundedUtils.drawRound((float) this.getX() + 80, (float) this.getY() + 200, 225, 30 + 0.5F, 6, ColorUtils.getBackgroundColor(1));
    	RoundedUtils.drawRound((float) this.getX() + 80, (float) this.getY() + 200, 228, 30F, 0, ColorUtils.getBackgroundColor(1));

    	//Search bar
    	RoundedUtils.drawRound(this.getX() + 95, this.getY() + 205, 135, 21, 6, ColorUtils.getBackgroundColor(4));
    	configNameField.drawTextBox();
    	
		configNameField.xPosition = (int) (this.getX() + 95);
		configNameField.yPosition = (int) (this.getY() + 207);
		
    	//Save bar
    	RoundedUtils.drawRound(this.getX() + 235, this.getY() + 205, 60, 21, 6, ColorUtils.getBackgroundColor(4));
    	FontUtils.regular20.drawString("Save", this.getX() + 254, this.getY() + 213, ColorUtils.getFontColor(1).getRGB());
    	
        final MouseUtils.Scroll scroll = MouseUtils.scroll();

        if(scroll != null && NekoCat.instance.configManager.getConfigs().size() > 5) {
        	switch (scroll) {
        	case DOWN:
        		if(NekoCat.instance.configManager.getScrollY() > -((NekoCat.instance.configManager.getConfigs().size() - 5.5) * 35)) {
        			NekoCat.instance.configManager.setScrollY(NekoCat.instance.configManager.getScrollY() - 20);
        		}
        		
        		if(NekoCat.instance.configManager.getScrollY() < -((NekoCat.instance.configManager.getConfigs().size() - 6) * 35)) {
        			NekoCat.instance.configManager.setScrollY(-((NekoCat.instance.configManager.getConfigs().size() - 5.2) * 35));
        		}
        		break;
            case UP:
        		if(NekoCat.instance.configManager.getScrollY() < -10) {
        			NekoCat.instance.configManager.setScrollY(NekoCat.instance.configManager.getScrollY() + 20);
        		}else {
            		if(NekoCat.instance.configManager.getConfigs().size() > 5) {
            			NekoCat.instance.configManager.setScrollY(0);
            		}
        		}
        		break;
        	}
        }
        
        scrollAnimation.setAnimation((float) NekoCat.instance.configManager.getScrollY(), 16);
        
    	if(MouseUtils.isInside(mouseX, mouseY, this.getX() + 80, this.getY(), 220, 199)) {
    		canToggle = true;
    	}else {
    		canToggle = false;
    	}
    	
    	if(!removed && removeFile != null) {
    		removed = true;
    		removeFile.delete();
    		removeFile = null;
    	}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		
    	int offsetY = 15;
    	
		configNameField.mouseClicked(mouseX, mouseY, mouseButton);
		
    	for(File f : NekoCat.instance.configManager.getConfigs()) {
    		
    		if(MouseUtils.isInside(mouseX, mouseY, (float) this.getX() + 95, (float) (this.getY() + offsetY + scrollAnimation.getValue()), 165, 26) && mouseButton == 0 && canToggle) {
    			
        		for(Mod m : NekoCat.instance.modManager.getMods()) {
        			m.setToggled(false);
        		}
        		
    			NekoCat.instance.configManager.load(f);
    			ClientUtils.showNotification("Config", "Config has been successfully loaded!");
    		}
    		if(MouseUtils.isInside(mouseX, mouseY, (float) this.getX() + 260, (float) (this.getY() + offsetY + scrollAnimation.getValue()), 35, 26) && mouseButton == 0) {
    			removed = false;
    			removeFile = f;
    		}
    		offsetY+= 35;
    	}
		
		if(MouseUtils.isInside(mouseX, mouseY, this.getX() + 235, this.getY() + 205, 60, 21) && mouseButton == 0) {
			
			String configName = configNameField.getText();
			
			NekoCat.instance.configManager.save(new File(NekoCat.instance.fileManager.getConfigDir(), configName + ".txt"));
			
			configNameField.setText("");
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		
	}
	
	@Override
	public void updateScreen() {
		configNameField.updateCursorCounter();
	}
	
	@Override
	public void keyTyped(char typedChar, int keyCode) {
		configNameField.textboxKeyTyped(typedChar, keyCode);
	}
}
