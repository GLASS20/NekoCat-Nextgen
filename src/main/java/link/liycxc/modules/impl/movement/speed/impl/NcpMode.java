package link.liycxc.modules.impl.movement.speed.impl;

import link.liycxc.api.events.impl.EventUpdate;
import link.liycxc.modules.impl.movement.speed.SpeedMode;
import link.liycxc.utils.player.MoveUtil;
import link.liycxc.api.events.EventTarget;
import link.liycxc.utils.PlayerUtils;
import net.minecraft.potion.Potion;

/**
 * This file is part of LikeSoar project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-10
 * @time: 15:32
 */
public class NcpMode extends SpeedMode {
    public NcpMode() {
        super("NCP");
    }

    private boolean wasSlow = false;

    @Override
    public void onDisable() {
        mc.thePlayer.jumpMovementFactor = 0.02f;
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.thePlayer.ticksExisted % 20 <= 9) {
            mc.timer.timerSpeed = 1.05f;
        } else {
            mc.timer.timerSpeed = 0.98f;
        }

        if (MoveUtil.isMoving()) {
            if (mc.thePlayer.onGround) {
                wasSlow = false;
                mc.thePlayer.jump();
                MoveUtil.strafe(0.48f);
                if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
                    MoveUtil.strafe(0.48f * (1.0f + 0.13f * (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1)));
                }
            }
            MoveUtil.strafe(PlayerUtils.getSpeed() * 1.007f);
            if (PlayerUtils.getSpeed() < 0.277) {
                wasSlow = true;
            }
            if (wasSlow) {
                MoveUtil.strafe(0.277f);
            }
        } else {
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
            wasSlow = true;
        }
    }
}
