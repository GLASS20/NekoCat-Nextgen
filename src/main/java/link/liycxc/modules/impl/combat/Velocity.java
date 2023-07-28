package link.liycxc.modules.impl.combat;

import link.liycxc.api.value.impl.ListValue;
import link.liycxc.utils.player.MoveUtil;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventReceivePacket;
import link.liycxc.api.tags.ModuleTag;
import link.liycxc.modules.Module;
import link.liycxc.modules.ModuleCategory;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;

@ModuleTag
public class Velocity extends Module {
    public Velocity() {
        super("Velocity","No kb", ModuleCategory.Combat);
    }

    private final ListValue mode = new ListValue("Mode",new String[]{"Silky", "Vulcan"},"Silky");

    @EventTarget
    public void onRPacket(EventReceivePacket event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof S12PacketEntityVelocity) {
            if (((S12PacketEntityVelocity) packet).entityID != mc.thePlayer.getEntityId()) {
                return;
            }
            switch(mode.get()) {
                case "Silky": {
                    event.setCancelled(true);
                    if (mc.thePlayer.onGround) {
                        mc.thePlayer.motionY = ((S12PacketEntityVelocity) packet).getMotionY() / 8000.0;
                        if (mc.thePlayer.isPotionActive(Potion.moveSpeed) && MoveUtil.isMoving()) MoveUtil.strafe();
                    }
                    break;
                }
                case "Vulcan": {
                    event.setCancelled(true);
                    break;
                }
            }
        }

        if (packet instanceof C0FPacketConfirmTransaction) {
            switch (mode.get()) {
                case "Vulcan": {
                    int transUID = ((C0FPacketConfirmTransaction) packet).getUid();
                    if (transUID >= -31767 && transUID <= -30769) {
                        event.setCancelled(true);
                    }

                    break;
                }
            }
        }
    }
}
