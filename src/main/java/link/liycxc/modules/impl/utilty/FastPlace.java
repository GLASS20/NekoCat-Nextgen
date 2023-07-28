package link.liycxc.modules.impl.utilty;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.IntValue;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.manages.component.impl.SlotComponent;
import link.liycxc.modules.Module;
import link.liycxc.modules.ModuleCategory;
import net.minecraft.item.ItemBlock;

@ModuleTag
public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace","FastPlace", ModuleCategory.Util);
    }

    private final IntValue delay = new IntValue("Delay", 0, 0, 3);

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (SlotComponent.getItemStack() != null && SlotComponent.getItemStack().getItem() instanceof ItemBlock) {
            mc.rightClickDelayTimer = Math.min(mc.rightClickDelayTimer, this.delay.get());
        }
    }
}
