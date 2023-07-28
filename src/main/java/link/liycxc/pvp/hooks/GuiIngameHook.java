package link.liycxc.pvp.hooks;

import link.liycxc.utils.animation.simple.SimpleAnimation;
import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventRender2D;
import link.liycxc.api.events.impl.EventRenderDamageTint;
import link.liycxc.api.events.impl.EventRenderShadow;
import link.liycxc.pvp.GuiEditHUD;
import link.liycxc.pvp.management.mods.impl.HUDMod;
import link.liycxc.pvp.management.mods.impl.MenuBlurMod;
import link.liycxc.ui.notification.NotificationManager;
import link.liycxc.utils.GlUtils;
import link.liycxc.utils.color.ColorUtils;
import link.liycxc.utils.render.RoundedUtils;
import link.liycxc.utils.render.StencilUtils;
import link.liycxc.utils.shader.BlurUtils;
import link.liycxc.utils.shader.GaussianBlur;
import link.liycxc.utils.shader.ShadowUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;

import static link.liycxc.NekoCat.mc;

public class GuiIngameHook {

	private static Framebuffer shadowFramebuffer = new Framebuffer(1, 1, false);
	private static SimpleAnimation opacityAnimation = new SimpleAnimation(0.0F);
	
	public static void renderGameOverlay(float partialTicks) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		opacityAnimation.setAnimation(Minecraft.getMinecraft().currentScreen instanceof GuiEditHUD ? 220 : 0, 16);
		
		RoundedUtils.drawRound(0, sr.getScaledHeight() / 2, sr.getScaledWidth(), 1, 0, ColorUtils.getBackgroundColor(4, (int) opacityAnimation.getValue()));
		RoundedUtils.drawRound(sr.getScaledWidth() / 2, 0, 1, sr.getScaledHeight(), 0, ColorUtils.getBackgroundColor(4, (int) opacityAnimation.getValue()));
		
		EventRenderDamageTint event3 = new EventRenderDamageTint();
		event3.call();
		
		if((!NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Hide Debug Menu").getValBoolean()) || (NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Hide Debug Menu").getValBoolean() && !Minecraft.getMinecraft().gameSettings.showDebugInfo)) {
			EventRender2D event = new EventRender2D(partialTicks, mc.scaledresolution);
			EventRenderShadow event2 = new EventRenderShadow(partialTicks);
			
	        shadowFramebuffer = GlUtils.createFrameBuffer(shadowFramebuffer);

	        shadowFramebuffer.framebufferClear();
	        shadowFramebuffer.bindFramebuffer(true);
	        event2.call();
	        shadowFramebuffer.unbindFramebuffer();

	        ShadowUtils.renderShadow(shadowFramebuffer.framebufferTexture, 8, 2);
	        
	        StencilUtils.initStencilToWrite();
	        if(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Blur").getValBoolean()) {
	            event2.call();
	        }
	        
	        StencilUtils.readStencilBuffer(1);
	        GaussianBlur.renderBlur(NekoCat.instance.settingsManager.getSettingByClass(HUDMod.class, "Blur Radius").getValInt());
	        StencilUtils.uninitStencilBuffer();
	        
	        event.call();
		}
		
		if(Minecraft.getMinecraft().currentScreen instanceof Gui) {
	        if(NekoCat.instance.modManager.getModByClass(MenuBlurMod.class).isToggled()) {
	        	BlurUtils.drawBlurScreen();
	        }	
		}
		
		NotificationManager.render();
        
        GlStateManager.enableBlend();
	}
}
