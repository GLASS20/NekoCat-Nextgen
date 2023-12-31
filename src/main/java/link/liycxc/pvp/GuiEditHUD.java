package link.liycxc.pvp;

import link.liycxc.utils.mouse.MouseUtils;
import link.liycxc.NekoCat;
import link.liycxc.pvp.management.mods.Mod;
import link.liycxc.pvp.management.mods.ModCategory;
import link.liycxc.pvp.management.mods.impl.ScoreboardMod;
import link.liycxc.utils.render.ClickEffect;
import link.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiEditHUD extends GuiScreen{
	
    private List<ClickEffect> clickEffects = new ArrayList<>();

    private boolean fromClickGUI;
    
    public GuiEditHUD(boolean fromClickGUI) {
    	this.fromClickGUI = fromClickGUI;
    }
    
	@Override
	public void initGui() {
		for(Mod m : NekoCat.instance.modManager.getMods()) {
			m.setDragging(false);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for(Mod m : NekoCat.instance.modManager.getMods()) {
			if(m.isToggled() && m.getCategory().equals(ModCategory.HUD)) {

				boolean isInside = m.getName().equals("Scoreboard") ?  MouseUtils.isInside(mouseX, mouseY, ScoreboardMod.shadowX, ScoreboardMod.shadowY, ScoreboardMod.shadowWidth - ScoreboardMod.shadowX, ScoreboardMod.shadowHeight - ScoreboardMod.shadowY)
						: MouseUtils.isInside(mouseX, mouseY, m.getX(), m.getY(), m.getWidth() - m.getX(), m.getHeight() - m.getY()) && 
						NekoCat.instance.modManager.getMods().stream().filter(m2 -> m2.isToggled() && m2.getCategory().equals(ModCategory.HUD) && mouseX >= m2.getX() && mouseX <= m2.getWidth() && mouseY >= m2.getY() && mouseY <= m2.getHeight()).findFirst().get().equals(m);
				
				m.editOpacityAnimation.setAnimation(isInside ?  255 : 0, 12);
				
				if(m.getClass().equals(ScoreboardMod.class)) {
					RoundedUtils.drawRoundOutline(ScoreboardMod.shadowX - 4, ScoreboardMod.shadowY - 4, (ScoreboardMod.shadowWidth - ScoreboardMod.shadowX) + 8, (ScoreboardMod.shadowHeight - ScoreboardMod.shadowY) + 8, 6, 1, new Color(255, 255, 255, 0), new Color(255, 255, 255, (int) m.editOpacityAnimation.getValue()));
				}else {
					RoundedUtils.drawRoundOutline(m.getX() - 4, m.getY() - 4, (m.getWidth() - m.getX()) + 8, (m.getHeight() - m.getY()) + 8, 6, 1, new Color(255, 255, 255, 0), new Color(255, 255, 255, (int) m.editOpacityAnimation.getValue()));
				}
				
				if(m.isDragging()) {
					m.setX(mouseX + m.getDraggingX());
					m.setY(mouseY + m.getDraggingY());
				}
			}
		}

         super.drawScreen(mouseX, mouseY, partialTicks);
         
         if(clickEffects.size() > 0) {
             Iterator<ClickEffect> clickEffectIterator= clickEffects.iterator();
             while(clickEffectIterator.hasNext()){
                 ClickEffect clickEffect = clickEffectIterator.next();
                 clickEffect.draw();
                 if (clickEffect.canRemove()) clickEffectIterator.remove();
             }
         }
	}
	
    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
    	if(keyCode == 1) {
    		if(fromClickGUI) {
        		mc.displayGuiScreen(NekoCat.instance.guiManager.getClickGUI());
    		}else {
    			mc.displayGuiScreen(null);
    		}
    	}
    }
    
	@Override
	public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		
        ClickEffect clickEffect = new ClickEffect(mouseX, mouseY);
        clickEffects.add(clickEffect);
        
		for(Mod m : NekoCat.instance.modManager.getMods()) {
			if(m.isToggled() && m.getCategory().equals(ModCategory.HUD)) {
				
				boolean isInside = m.getName().equals("Scoreboard") ?  MouseUtils.isInside(mouseX, mouseY, ScoreboardMod.shadowX, ScoreboardMod.shadowY, ScoreboardMod.shadowWidth - ScoreboardMod.shadowX, ScoreboardMod.shadowHeight - ScoreboardMod.shadowY)
						: MouseUtils.isInside(mouseX, mouseY, m.getX(), m.getY(), m.getWidth() - m.getX(), m.getHeight() - m.getY()) && 
						NekoCat.instance.modManager.getMods().stream().filter(m2 -> m2.isToggled() && m2.getCategory().equals(ModCategory.HUD) && mouseX >= m2.getX() && mouseX <= m2.getWidth() && mouseY >= m2.getY() && mouseY <= m2.getHeight()).findFirst().get().equals(m);
				
				if(isInside) {
					m.setDragging(true);
					m.setDraggingX(m.getX() - mouseX);
					m.setDraggingY(m.getY() - mouseY);
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state) {  
		for(Mod m : NekoCat.instance.modManager.getMods()){
			m.setDragging(false);
		}
	}
	
	@Override
	public void onGuiClosed() {
		for(Mod m : NekoCat.instance.modManager.getMods()){
			m.setDragging(false);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
