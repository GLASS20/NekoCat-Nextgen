package link.liycxc.manages.component.impl;

import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventReceivePacket;
import link.liycxc.manages.component.Component;
import link.liycxc.utils.PlayerUtils;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class InventoryDeSyncComponent extends Component {

    private static boolean active, deSynced;

    @EventTarget
    public void onPacketReceive(EventReceivePacket event) {
        Packet<?> p = event.getPacket();

        if (p instanceof S2DPacketOpenWindow) {
            if (active) {
                event.setCancelled(true);
                deSynced = true;
                active = false;
            }
        }
    };

    public static void setActive(String command) {
        if (active || deSynced || mc.currentScreen != null) {
            return;
        }

        PlayerUtils.tellPlayer(command);
        active = true;
    }

    public static boolean isDeSynced() {
        return deSynced;
    }
}
