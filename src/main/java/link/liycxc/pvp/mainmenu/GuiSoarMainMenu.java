package link.liycxc.pvp.mainmenu;

import link.liycxc.pvp.credit.GuiCredit;
import link.liycxc.NekoCat;
import link.liycxc.pvp.GuiAccountManager;
import link.liycxc.pvp.GuiPleaseLogin;
import link.liycxc.pvp.management.mods.impl.ClientMod;
import link.liycxc.utils.DayEventUtils;
import link.liycxc.utils.GlUtils;
import link.liycxc.utils.animation.normal.Animation;
import link.liycxc.utils.animation.normal.Direction;
import link.liycxc.utils.animation.normal.impl.EaseBackIn;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.utils.font.FontUtils;
import link.liycxc.utils.mouse.MouseUtils;
import link.liycxc.utils.render.ClickEffect;
import link.liycxc.utils.render.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiSoarMainMenu extends GuiScreen{

	private ArrayList<SoarMainMenuButton> menus = new ArrayList<SoarMainMenuButton>();
	private Animation introAnimation;
	private boolean closeIntro;
    
    private List<ClickEffect> clickEffects = new ArrayList<>();
    
    private CloseType closeType;

	public GuiSoarMainMenu() {
		menus.add(new SoarMainMenuButton("Singleplayer"));
		menus.add(new SoarMainMenuButton("Multiplayer"));
		menus.add(new SoarMainMenuButton("Account Manager"));
		menus.add(new SoarMainMenuButton("Options"));
		menus.add(new SoarMainMenuButton("Quit"));
	}

	@Override
	public void initGui() {
		if(NekoCat.instance.accountManager.isFirstLogin) {
			mc.displayGuiScreen(new GuiPleaseLogin(this));
		}
        introAnimation = new EaseBackIn(450, 1, 1.5F);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		Color bg1Color = ColorUtils.getBackgroundColor(1);
		Color bg3Color = ColorUtils.getBackgroundColor(3);
		Color font1Color = ColorUtils.getFontColor(1);
		Color font2Color = ColorUtils.getFontColor(2);

		if(DayEventUtils.isHalloween()) {
			bg1Color = ColorUtils.getHalloweenColor().getLightPurple();
			bg3Color = ColorUtils.getHalloweenColor().getLightOrange();
			font1Color = ColorUtils.getHalloweenColor().getBlack();
			font2Color = ColorUtils.getHalloweenColor().getBlack();
		}

		if(DayEventUtils.isChristmas()) {
			bg1Color = ColorUtils.getChristmasColor().getGreen();
			bg3Color = ColorUtils.getChristmasColor().getRed();
			font1Color = Color.WHITE;
			font2Color = Color.WHITE;
		}

		GlStateManager.disableTexture2D();

		if(closeIntro) {
			introAnimation.setDirection(Direction.BACKWARDS);
			if(introAnimation.isDone(Direction.BACKWARDS)) {
				closeIntro = false;
				if(closeType.equals(CloseType.ACCOUNT)) {
					mc.displayGuiScreen(new GuiAccountManager(this));
				}else if(closeType.equals(CloseType.CREDIT)) {
					mc.displayGuiScreen(new GuiCredit());
				}
			}
		}

		int addX = 65;
		int addY = 85;
        int offsetY = -45;

		RoundedUtils.drawRound(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0, bg1Color);

		FontUtils.icon24.drawString("N", 10, 10, font1Color.getRGB());
		FontUtils.icon24.drawString("Q", sr.getScaledWidth() - 20, 10, font1Color.getRGB());

		GlUtils.startScale(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, (float) introAnimation.getValue());

		RoundedUtils.drawRound(sr.getScaledWidth() / 2 - addX, sr.getScaledHeight() / 2 - addY, addX * 2, addY * 2, 6, bg3Color);

		FontUtils.regular_bold40.drawStringWithClientColor(NekoCat.instance.getName(), (sr.getScaledWidth() / 2 - addX) - FontUtils.regular_bold40.getStringWidth(NekoCat.instance.getName()) + 107, sr.getScaledHeight() / 2 - 80, false);

		for(SoarMainMenuButton b : menus) {

			boolean isInside = MouseUtils.isInside(mouseX, mouseY, sr.getScaledWidth() / 2 - addX, sr.getScaledHeight() / 2 - addY + offsetY + 78, addX * 2, 20);

			b.opacityAnimation.setAnimation(isInside ? 255 : 0, 10);

			RoundedUtils.drawRound(sr.getScaledWidth() / 2 - addX, sr.getScaledHeight() / 2 - addY + offsetY + 78, addX * 2, 20, 6, getSelectButtonColor(((int) b.opacityAnimation.getValue())));
			FontUtils.regular20.drawCenteredString(b.getName(), sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 + offsetY, font2Color.getRGB());

			offsetY+=26;
		}

		GlUtils.stopScale();
		super.drawScreen(mouseX, mouseY, partialTicks);

		//Copyright
        FontUtils.regular20.drawString("Copyright Mojang AB. Do not distribute!", sr.getScaledWidth() - FontUtils.regular_bold20.getStringWidth("Copyright Mojang AB. Do not distribute!") + 4, sr.getScaledHeight() - FontUtils.regular_bold20.getHeight() - 3, font2Color.getRGB());
        
        if(clickEffects.size() > 0) {
            Iterator<ClickEffect> clickEffectIterator= clickEffects.iterator();
            while(clickEffectIterator.hasNext()){
                ClickEffect clickEffect = clickEffectIterator.next();
                clickEffect.draw();
                if (clickEffect.canRemove()) clickEffectIterator.remove();
            }
        }

		// String test = ChatFormatting.OBFUSCATED + "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz 使用开源的更莎黑体，测试中文渲染，日文：の";
		// FontManager.test18.drawString(test, width - FontManager.test18.getStringWidth(test), height - FontManager.test18.getHalfHeight(), -1);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        
        ClickEffect clickEffect = new ClickEffect(mouseX, mouseY);
        clickEffects.add(clickEffect);

		if(mouseButton == 0) {

			if(MouseUtils.isInside(mouseX, mouseY, 2, 2, 30, 30)) {
				NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "DarkMode").setValBoolean(!NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "DarkMode").getValBoolean());
			}
            
            if (MouseUtils.isInside(mouseX, mouseY, sr.getScaledWidth() - 25, 5.0, 20.0, 20.0)) {
				closeType = CloseType.CREDIT;
				closeIntro = true;
            }

			int addX = 65;
			int addY = 85;
			int offsetY = -45;

			for(SoarMainMenuButton b : menus) {
				if(MouseUtils.isInside(mouseX, mouseY, sr.getScaledWidth() / 2 - addX, sr.getScaledHeight() / 2 - addY + offsetY + 78, addX * 2, 20)){
					switch(b.getName()) {
						case "Singleplayer":
							mc.displayGuiScreen(new GuiSelectWorld(this));
							break;
						case "Multiplayer":
							mc.displayGuiScreen(new GuiMultiplayer(this));
							break;
						case "Account Manager":
							closeType = CloseType.ACCOUNT;
							closeIntro = true;
							break;
						case "Options":
							mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
							break;
						case "Quit":
							mc.shutdown();
							break;
					}
				}
				offsetY+=26;
			}
		}
	}

	private Color getSelectButtonColor(int opacity) {

		if(DayEventUtils.isHalloween()) {
			return ColorUtils.getHalloweenColor().getOrange(opacity);
		}

		if(DayEventUtils.isChristmas()) {
			return ColorUtils.getChristmasColor().getRed(opacity).brighter();
		}

		return ColorUtils.getBackgroundColor(4,  opacity);
	}
}
