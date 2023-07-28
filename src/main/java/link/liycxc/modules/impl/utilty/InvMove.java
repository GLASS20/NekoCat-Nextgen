package link.liycxc.modules.impl.utilty;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.modules.Module;
import link.liycxc.modules.ModuleCategory;

@ModuleTag
public class InvMove extends Module {
    public InvMove() {
        super("InvMove","You can move when you open gui", ModuleCategory.Util);
    }
}
