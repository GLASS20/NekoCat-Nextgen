package link.liycxc.modules.impl.movement;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.BoolValue;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.manages.component.impl.SlotComponent;
import link.liycxc.modules.Module;
import link.liycxc.modules.ModuleCategory;
import net.minecraft.item.ItemBlock;
import org.lwjgl.input.Keyboard;

/**
 * @author Liycxc
 */
@ModuleTag
public class SafeWalk extends Module {
    public SafeWalk() {
        super("SafeWalk", "Safe Walk", ModuleCategory.Movement, Keyboard.KEY_X);
    }

    private final BoolValue blocksOnly = new BoolValue("Blocks Only", false);
    private final BoolValue backwardsOnly = new BoolValue("Backwards Only", false);

    @EventTarget
    public void onPreUpdate(EventPreMotion event){

        mc.thePlayer.safeWalk = mc.thePlayer.onGround && (!mc.gameSettings.keyBindForward.isKeyDown() || !backwardsOnly.getValue()) &&
                ((SlotComponent.getItemStack() != null && SlotComponent.getItemStack().getItem() instanceof ItemBlock) ||
                        !this.blocksOnly.getValue());
    };
}
