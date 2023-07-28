package link.liycxc;

import com.logisticscraft.occlusionculling.DataProvider;
import com.logisticscraft.occlusionculling.OcclusionCullingInstance;
import io.netty.buffer.Unpooled;
import link.liycxc.api.events.impl.*;
import link.liycxc.modules.ModuleConfig;
import link.liycxc.modules.ModuleManager;
import link.liycxc.pvp.GuiEditHUD;
import link.liycxc.pvp.management.colors.ColorManager;
import link.liycxc.pvp.management.config.ConfigManager;
import link.liycxc.pvp.management.cosmetics.CosmeticManager;
import link.liycxc.pvp.management.image.ImageManager;
import link.liycxc.pvp.management.keybinds.KeyBindManager;
import link.liycxc.pvp.management.music.MusicManager;
import link.liycxc.pvp.management.quickplay.QuickPlayManager;
import link.liycxc.pvp.management.settings.SettingsManager;
import link.liycxc.utils.*;
import link.liycxc.utils.culling.CullTask;
import link.liycxc.utils.font.FontManager;
import link.liycxc.api.events.EventManager;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.*;
import link.liycxc.manages.BotManager;
import link.liycxc.manages.FileManager;
import link.liycxc.manages.TargetManager;
import link.liycxc.manages.component.ComponentManager;
import link.liycxc.pvp.management.account.AccountManager;
import link.liycxc.pvp.management.discord.DiscordManager;
import link.liycxc.pvp.management.gui.GuiManager;
import link.liycxc.pvp.management.mods.ModManager;
import link.liycxc.pvp.management.mods.impl.ClientMod;
import link.liycxc.pvp.management.mods.impl.ForgeSpooferMod;
import link.liycxc.utils.*;
import link.liycxc.utils.font.FontUtils;
import link.liycxc.utils.server.HypixelUtils;
import link.liycxc.utils.server.ServerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;

import java.util.Random;

/**
 * @author Liycxc
 */
public class NekoCat {

	public String getName() {
		return "NekoCat";
	}

	public String getVersion() {
		return "114514";
	}

	public static NekoCat instance = new NekoCat();
	public static Minecraft mc = Minecraft.getMinecraft();
	public final boolean DEVELOPMENT_SWITCH = true;

	// Soar Client Managers
	public FileManager fileManager;
	public ImageManager imageManager;
	public DiscordManager discordManager;
	public KeyBindManager keyBindManager;
	public SettingsManager settingsManager;
	public EventManager eventManager;
	public ModManager modManager;
	public GuiManager guiManager;
	public ColorManager colorManager;
	public CosmeticManager cosmeticManager;
	public ConfigManager configManager;
	public MusicManager musicManager;
	public AccountManager accountManager;
	public QuickPlayManager quickPlayManager;

	// NekoCat Managers
	public ModuleManager moduleManager;
	public TargetManager targetManager;
	public BotManager botManager;
	public ComponentManager componentManager;
	public ModuleConfig moduleConfig;

	private boolean loaded;
	private long playTime;
	private final TimerUtils apiTimer = new TimerUtils();

	
	public void startClient() {
		
		OSType os = OSType.getType();

		// Managers initializing
		{
			// Soar
			fileManager = new FileManager();
			imageManager = new ImageManager();
			keyBindManager = new KeyBindManager();
			settingsManager = new SettingsManager();
			eventManager = new EventManager();
			modManager = new ModManager();
			guiManager = new GuiManager();
			colorManager = new ColorManager();
			cosmeticManager = new CosmeticManager();
			configManager = new ConfigManager();
			musicManager = new MusicManager();
			accountManager = new AccountManager();
			quickPlayManager = new QuickPlayManager();

			// NekoCat
			moduleManager = new ModuleManager();
			moduleConfig = new ModuleConfig();
			targetManager = new TargetManager();
			botManager = new BotManager();
			componentManager = new ComponentManager();
		}

		// Fonts initializing
		{
			FontUtils.init();
			FontManager.init();
			targetManager.init();
			botManager.init();
			componentManager.init();
		}

		eventManager.register(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.start();
			discordManager.update("Playing NekoCat Client v" + NekoCat.instance.getVersion(), "");
		}
		
		startCull();

		mc.gameSettings.loadOptions();
		DayEventUtils.resetHudDesign();
		moduleManager.registerModules();

		// First load file
		moduleConfig.load(fileManager.getNConfigFile());

		// Second register modules event
		moduleManager.EventRegister();

		// Verify.init();
	}
	
	public void stopClient() {
		
		OSType os = OSType.getType();
		
		eventManager.unregister(this);
		
		if(os == OSType.WINDOWS) {
			discordManager = new DiscordManager();
			discordManager.update("Playing NekoCat Client v" + NekoCat.instance.getVersion(), "");
		}

		moduleConfig.save(fileManager.getNConfigFile());
	}
    
	@EventTarget
	public void onTick(EventTick event) {
		
    	boolean isRandom = NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "Random").getValBoolean();
    	boolean isLoop = NekoCat.instance.settingsManager.getSettingByClass(ClientMod.class, "Loop").getValBoolean();

		if(isRandom || isLoop) {
			if(!loaded) {
				if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() >= musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
					loaded = true;
					new Thread(() -> {
						loaded = false;
						musicManager.getCurrentMusic().mediaPlayer.stop();

						if(isRandom) {
							Random random = new Random();
							int randomIndex = random.nextInt(NekoCat.instance.musicManager.getMusics().size() + 1);
							musicManager.setCurrentMusic(NekoCat.instance.musicManager.getMusics().get(randomIndex == 10 ? randomIndex - 1 : randomIndex));
						}

						musicManager.getCurrentMusic().playMusic();
					}).start();
				}
			}
		}else {
			loaded = false;
			if(musicManager.getCurrentMusic() != null && musicManager.getCurrentMusic().mediaPlayer != null && musicManager.getCurrentMusic().mediaPlayer.getCurrentTime().toSeconds() == musicManager.getCurrentMusic().mediaPlayer.getTotalDuration().toSeconds()) {
				musicManager.getCurrentMusic().mediaPlayer.stop();
			}
		}

		// try turn off optifine fast render
		try {
			ClientUtils.gameSettings_ofFastRender.set(mc.gameSettings, false);
		} catch (Exception ignored) {}
		
		mc.gameSettings.useVbo = true;
		mc.gameSettings.fboEnable = true;
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		TargetUtils.onUpdate();
	}
	
	private void startCull() {
		CullTask cullingTask = new CullTask(new OcclusionCullingInstance(128, new DataProvider() {

			private WorldClient world;

			@Override
			public boolean prepareChunk(int x, int z) {
				return (world = mc.theWorld) != null;
			}

			@Override
			public boolean isOpaqueFullCube(int x, int y, int z) {
				return world.isBlockNormalCube(new BlockPos(x, y, z), false);
			}

		}));

		Thread generalUpdateThread = new Thread(() -> {
			while(mc.running) {
				try {
					Thread.sleep(10);
				}
				catch(InterruptedException error) {
					return;
				}

				cullingTask.run();
			}
		}, "Async Updates");
		generalUpdateThread.setUncaughtExceptionHandler((thread, error) -> {

		});
		generalUpdateThread.start();
	}
	
	@EventTarget
	public void onSendPacket(EventSendPacket event) {
        if (event.getPacket() instanceof C17PacketCustomPayload) {
        	
            C17PacketCustomPayload packet = (C17PacketCustomPayload) event.getPacket();
            
            if(modManager.getModByClass(ForgeSpooferMod.class).isToggled()) {
                (packet).setData(new PacketBuffer(Unpooled.buffer()).writeString("FML"));
            } else {
                (packet).setData(new PacketBuffer(Unpooled.buffer()).writeString("Lunar-Client"));
            }
        }
	}
    
    @EventTarget
    public void onReceivePacket(EventReceivePacket event) {
    	if(ServerUtils.isHypixel()) {
            if (event.getPacket() instanceof S02PacketChat) {
                final S02PacketChat chatPacket = (S02PacketChat)event.getPacket();
                final String chatMessage = chatPacket.getChatComponent().getUnformattedText();
                if (chatMessage.matches("Your new API key is ........-....-....-....-............")) {
                    event.setCancelled(true);
                    HypixelUtils.setApiKey(chatMessage.replace("Your new API key is ", ""));
                }
            }
    	}
    }
    
    @EventTarget
    public void onRespawn(EventRespawn event) {
    	if(ServerUtils.isHypixel()) {
        	HypixelUtils.setApiKey(null);
    	}
    }
    
    @EventTarget
    public void onPreUpdate(EventPreMotion event) {
//        if (ServerUtils.isHypixel() && apiTimer.delay(3000) && HypixelUtils.getApiKey() == null) {
//            mc.thePlayer.sendChatMessage("/api new");
//            apiTimer.reset();
//        }
    }
    
    @EventTarget
    public void onKey(EventKey event) {
    	
    	if(mc.thePlayer != null && mc.theWorld != null) {
        	if(event.getKey() == keyBindManager.EDITHUD.getKeyCode()) {
        		mc.displayGuiScreen(new GuiEditHUD(false));
        	}
    	}
    }

	public long getPlayTime() {
		return playTime;
	}

	public void setPlayTime(long playTime) {
		this.playTime = playTime;
	}
}
