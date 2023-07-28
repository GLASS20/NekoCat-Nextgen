package link.liycxc.modules.impl.utilty.disabler.impl;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.BoolValue;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreUpdate;
import link.liycxc.modules.impl.utilty.disabler.DisablerMode;
import link.liycxc.utils.PlayerUtils;

/**
 * @author Liycxc
 */
@ModuleTag
public class WatchdogMode extends DisablerMode {
    public WatchdogMode() {
        super("Watchdog");
    }

    public BoolValue disable = new BoolValue("disable",true);

    @EventTarget
    public void onPreUpdate(EventPreUpdate event) {
        PlayerUtils.tellPlayer("Watchdog was " + (disable.get() ? "disable" : "enable"));
    }
}
