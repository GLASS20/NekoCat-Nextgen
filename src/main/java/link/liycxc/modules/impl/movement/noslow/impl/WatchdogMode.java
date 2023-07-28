package link.liycxc.modules.impl.movement.noslow.impl;

import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.api.events.impl.EventSendPacket;
import link.liycxc.api.events.impl.EventSlowDown;
import link.liycxc.manages.component.impl.RotationComponent;
import link.liycxc.modules.impl.movement.noslow.NoSlowMode;
import link.liycxc.utils.player.MoveUtil;
import link.liycxc.utils.player.MovementFix;
import link.liycxc.utils.player.PacketUtil;
import link.liycxc.utils.vector.Vector2f;
import net.minecraft.item.*;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;


/**
 * @author LvZhiqiao
 */
public class WatchdogMode extends NoSlowMode {

    public WatchdogMode() {
        super("Watchdog");
    }

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (mc.thePlayer.isUsingItem() && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && MoveUtil.isMoving()) {
            PacketUtil.sendNoEvent(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem % 8 + 1));
            PacketUtil.sendNoEvent(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
        }
    }

    @EventTarget
    public void onSendPacket(EventSendPacket event) {
        final Packet<?> packet = event.getPacket();

        if (packet instanceof C08PacketPlayerBlockPlacement) {
            if (mc.gameSettings.keyBindUseItem.isKeyDown() && (mc.thePlayer.getHeldItem() != null && (mc.thePlayer.getHeldItem().getItem() instanceof ItemFood || mc.thePlayer.getHeldItem().getItem() instanceof ItemBucketMilk || (mc.thePlayer.getHeldItem().getItem() instanceof ItemPotion && !ItemPotion.isSplash(mc.thePlayer.getHeldItem().getMetadata())) || mc.thePlayer.getHeldItem().getItem() instanceof ItemBow))) {
                if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && !(((C08PacketPlayerBlockPlacement) packet).getPosition().equals(new BlockPos(-1, -1, -1))))
                    return;
                event.setCancelled(true);
                MovingObjectPosition position = mc.thePlayer.rayTraceCustom(mc.playerController.getBlockReachDistance(), mc.thePlayer.rotationYaw, 90f);
                if (position == null) return;
                RotationComponent.setRotations(new Vector2f(mc.thePlayer.rotationYaw, 90f), 10, MovementFix.OFF);
                sendUseItem(position);
            }
        }
    }

    @EventTarget
    public void onSlowDown(EventSlowDown event) {
        if (MoveUtil.isMoving()) {
            event.setCancelled(true);
        }
    }

    private void sendUseItem(MovingObjectPosition mouse) {
        final float facingX = (float) (mouse.hitVec.xCoord - (double) mouse.getBlockPos().getX());
        final float facingY = (float) (mouse.hitVec.yCoord - (double) mouse.getBlockPos().getY());
        final float facingZ = (float) (mouse.hitVec.zCoord - (double) mouse.getBlockPos().getZ());

        PacketUtil.sendNoEvent(new C08PacketPlayerBlockPlacement(mouse.getBlockPos(), mouse.sideHit.getIndex(), mc.thePlayer.getHeldItem(), facingX, facingY, facingZ));
    }
}
