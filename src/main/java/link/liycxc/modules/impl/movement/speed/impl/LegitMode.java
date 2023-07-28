package link.liycxc.modules.impl.movement.speed.impl;

import link.liycxc.api.events.impl.EventPreUpdate;
import link.liycxc.api.events.impl.EventStrafe;
import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.BoolValue;
import link.liycxc.api.value.impl.ListValue;
import link.liycxc.manages.component.impl.RotationComponent;
import link.liycxc.modules.impl.movement.speed.SpeedMode;
import link.liycxc.utils.player.MoveUtil;
import link.liycxc.utils.player.MovementFix;
import link.liycxc.utils.vector.Vector2f;
import link.liycxc.api.events.EventTarget;

@ModuleTag
public class LegitMode extends SpeedMode {
    public LegitMode() {
        super("Legit");
    }

    private final ListValue rotationExploit = new ListValue("Rotation Exploit Mode", new String[]{"Off","Rotate","Speed Equivalent"},"Speed Equivalent");
    private final BoolValue cpuSpeedUpExploit = new BoolValue("CPU SpeedUp Exploit", true);
    private final BoolValue noJumpDelay = new BoolValue("No Jump Delay", true);

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        switch (rotationExploit.get()) {
            case "Rotate":
                if (!mc.thePlayer.onGround)
                    RotationComponent.setRotations(new Vector2f(mc.thePlayer.rotationYaw + 45, mc.thePlayer.rotationPitch), 10, MovementFix.NORMAL);
                break;

            case "Speed Equivalent (Almost legit, Very hard to flag)":
                MoveUtil.useDiagonalSpeed();
                break;
        }

        if (noJumpDelay.getValue()) {
            mc.thePlayer.jumpTicks = 0;
        }

        if (cpuSpeedUpExploit.getValue()) {
            mc.timer.timerSpeed = 1.004f;
        }
    }

    @EventTarget
    public void strafe(EventStrafe event) {
        if (mc.thePlayer.onGround) {
            mc.thePlayer.jump();
        }
    }
}
