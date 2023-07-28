package link.liycxc.modules.impl.movement.noslow.impl;

import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.api.events.impl.EventSendPacket;
import link.liycxc.modules.impl.movement.noslow.NoSlowMode;
import link.liycxc.utils.TimerUtils;
import link.liycxc.utils.player.MoveUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.*;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.LinkedList;

/**
 * This file is part of LikeSoar project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-10
 * @time: 15:14
 */
public class BlocksMcMode extends NoSlowMode {
    public BlocksMcMode() {
        super("BlocksMC");
    }

    private boolean nextTemp = false;
    private boolean waitC03 = false;

    private final TimerUtils timer = new TimerUtils();
    private final LinkedList<Packet<?>> linkedList = new LinkedList<>();

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (!MoveUtil.isMoving()) {
            return;
        }

        if (timer.delay(230) && nextTemp) {
            nextTemp = false;
            mc.getNetHandler().addToSendQueueNoEvent(new C07PacketPlayerDigging(
                    C07PacketPlayerDigging.Action.RELEASE_USE_ITEM,
                    BlockPos.ORIGIN,
                    EnumFacing.DOWN
            ));
            if (!linkedList.isEmpty()) {
                boolean canAttack = false;
                for (Packet<?> packet : linkedList) {
                    if (packet instanceof C03PacketPlayer) {
                        canAttack = true;
                    }
                    if(!((packet instanceof C02PacketUseEntity || packet instanceof C0APacketAnimation) && !canAttack)) {
                        mc.getNetHandler().addToSendQueueNoEvent(packet);
                    }
                }
                linkedList.clear();
            }
        }
        if (!nextTemp) {
            boolean lastBlockingStat = mc.thePlayer.isBlocking();
            if (!lastBlockingStat) {
                return;
            }
            mc.getNetHandler().addToSendQueueNoEvent(new C08PacketPlayerBlockPlacement(BlockPos.ORIGIN, 255, mc.thePlayer.inventory.getCurrentItem(), 0F, 0F, 0F));
            waitC03 = true;
            timer.reset();
        }
    }

    @EventTarget
    public void onSendPacket(EventSendPacket event) {
        final Packet<?> packet = event.getPacket();
        if (nextTemp) {
            if ((packet instanceof C07PacketPlayerDigging || packet instanceof C08PacketPlayerBlockPlacement) && mc.thePlayer.isBlocking()) {
                event.setCancelled(true);
            } else if (packet instanceof C03PacketPlayer || packet instanceof C0APacketAnimation || packet instanceof C0BPacketEntityAction || packet instanceof C02PacketUseEntity || packet instanceof C07PacketPlayerDigging || packet instanceof C08PacketPlayerBlockPlacement) {
                if (waitC03 && packet instanceof C03PacketPlayer) {
                    waitC03 = false;
                    return;
                }
                linkedList.add(packet);
                event.setCancelled(true);
            }
        }
    }
}
