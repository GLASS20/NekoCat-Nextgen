package link.liycxc.manages.component.impl;

import link.liycxc.api.events.impl.EventSendPacket;
import link.liycxc.utils.player.PacketUtil;
import link.liycxc.utils.player.StopWatch;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventLoadWorld;
import link.liycxc.api.events.impl.EventServerJoin;
import link.liycxc.manages.component.Component;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BlinkComponent extends Component {
    public static final ConcurrentLinkedQueue<Packet<?>> packets = new ConcurrentLinkedQueue<>();
    public static boolean blinking, dispatch;
    public static ArrayList<Class<?>> exemptedPackets = new ArrayList<>();
    public static StopWatch exemptionWatch = new StopWatch();

    public static void setExempt(Class<?>... packets) {
        exemptedPackets = new ArrayList<>(Arrays.asList(packets));
        exemptionWatch.reset();
    }

    @EventTarget
    public void onPacketSend(EventSendPacket event) {
        if (mc.thePlayer == null) {
            packets.clear();
            exemptedPackets.clear();
            return;
        }

        if (mc.thePlayer.isDead || mc.isSingleplayer() || !mc.getNetHandler().doneLoadingTerrain) {
            packets.forEach(PacketUtil::sendNoEvent);
            packets.clear();
            blinking = false;
            exemptedPackets.clear();
            return;
        }

        final Packet<?> packet = event.getPacket();

        if (packet instanceof C00Handshake || packet instanceof C00PacketLoginStart ||
                packet instanceof C00PacketServerQuery || packet instanceof C01PacketPing ||
                packet instanceof C01PacketEncryptionResponse) {
            return;
        }

        if (blinking && !dispatch) {
            if (exemptionWatch.finished(100)) {
                exemptionWatch.reset();
                exemptedPackets.clear();
            }

            // PingSpoofComponent.spoofing = false;

            if (!event.isCancelled() && exemptedPackets.stream().noneMatch(packetClass ->
                    packetClass == packet.getClass())) {
                packets.add(packet);
                event.setCancelled(true);
            }
        } else if (packet instanceof C03PacketPlayer) {
            packets.forEach(PacketUtil::sendNoEvent);
            packets.clear();
            dispatch = false;
        }
    };

    public static void dispatch() {
        dispatch = true;
    }

    @EventTarget
    public void onWorldChange (EventLoadWorld event) {
        packets.clear();
        BlinkComponent.blinking = false;
    };

    @EventTarget
    public void onServerJoin (EventServerJoin event) {
        packets.clear();
        BlinkComponent.blinking = false;
    };
}
