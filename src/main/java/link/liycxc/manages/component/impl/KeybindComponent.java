package link.liycxc.manages.component.impl;

import link.liycxc.NekoCat;
import link.liycxc.api.events.impl.EventKey;
import link.liycxc.modules.Module;
import link.liycxc.api.events.EventTarget;
import link.liycxc.manages.component.Component;

import java.util.Map;

public class KeybindComponent extends Component {
    @EventTarget
    public void onKey(EventKey key) {
        if (mc.currentScreen != null) {
            return;
        }
        if (NekoCat.instance.moduleManager.keyBinds.containsKey(key.getKey())) {
            for (Map.Entry<Integer, Module> entry : NekoCat.instance.moduleManager.keyBinds.entries()) {
                if (entry.getKey().equals(key.getKey())) {
                    entry.getValue().toggle();
                }
            }
        }
    }
}
