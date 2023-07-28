package link.liycxc.pvp.management.gui;

import link.liycxc.pvp.GuiQuickPlay;
import link.liycxc.pvp.mainmenu.GuiSoarMainMenu;
import link.liycxc.ui.clickgui.ClickGui;
import link.liycxc.pvp.clickgui.ClickGUI;
import link.liycxc.pvp.screenshot.GuiScreenshotViewer;

public class GuiManager {

	private ClickGUI clickGUI;
	private ClickGui gclickGUI;
	private GuiSoarMainMenu guiMainMenu;
	private GuiQuickPlay guiQuickPlay;
	private GuiScreenshotViewer guiScreenshotViewer;
	
	public GuiManager() {
		clickGUI = new ClickGUI();
		gclickGUI = new ClickGui();
		guiMainMenu = new GuiSoarMainMenu();
		guiQuickPlay = new GuiQuickPlay();
		guiScreenshotViewer = new GuiScreenshotViewer();
	}

	public ClickGUI getClickGUI() {
		return clickGUI;
	}

	public ClickGui getgClickGUI() {
		return gclickGUI;
	}

	public GuiSoarMainMenu getGuiMainMenu() {
		return guiMainMenu;
	}

	public GuiQuickPlay getGuiQuickPlay() {
		return guiQuickPlay;
	}

	public GuiScreenshotViewer getGuiScreenshotViewer() {
		return guiScreenshotViewer;
	}
}
