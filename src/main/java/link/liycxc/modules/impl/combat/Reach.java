package link.liycxc.modules.impl.combat;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.FloatValue;
import link.liycxc.modules.Module;
import link.liycxc.modules.ModuleCategory;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventMouseOver;
import link.liycxc.api.events.impl.EventPreMotion;
import org.lwjgl.input.Mouse;

@ModuleTag
public class Reach extends Module {
    public Reach() {
        super("Reach","Like long arm monkeys", ModuleCategory.Combat);
    }
    public static FloatValue maxValue = new FloatValue("Reach value",4f,3f,5f);

    private int exempt = 0;

    @EventTarget
    public void onPreMotion (EventPreMotion eventMotion) {
        exempt--;
    }
    @EventTarget
    public void onMouseOver(EventMouseOver eventMouseOver) {
        if (Mouse.isButtonDown(1)) {
            exempt = 1;
        }

        if (exempt > 0) return;

        eventMouseOver.setRange(maxValue.get());
    }
}
