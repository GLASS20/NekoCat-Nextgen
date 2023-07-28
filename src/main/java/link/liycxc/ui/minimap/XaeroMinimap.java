package link.liycxc.ui.minimap;

import java.io.IOException;

import link.liycxc.ui.minimap.interfaces.InterfaceHandler;
import net.minecraft.client.Minecraft;

public class XaeroMinimap
{
    public static XaeroMinimap instance;
    public static Minecraft mc = Minecraft.getMinecraft();
    
    public void load() throws IOException {
        InterfaceHandler.loadPresets();
        InterfaceHandler.load();
    }
}
