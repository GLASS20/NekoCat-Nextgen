package link.liycxc.modules.impl.combat;

import link.liycxc.api.tags.ModuleTag;
import link.liycxc.api.value.impl.BoolValue;
import link.liycxc.modules.Module;
import link.liycxc.NekoCat;
import link.liycxc.api.events.EventTarget;
import link.liycxc.api.events.impl.EventPreMotion;
import link.liycxc.modules.ModuleCategory;
import net.minecraft.client.network.NetworkPlayerInfo;

@ModuleTag
public class AntiBot extends Module {
    public AntiBot() {
        super("AntiBot", "omg matrix bot", ModuleCategory.Combat);
    }

//    private final BoolValue advancedAntiBot = new BoolValue("Always Nearby Check", false);

    private final BoolValue watchdogAntiBot = new BoolValue("Watchdog Check", false);

/*    private final BoolValue funcraftAntiBot = new BoolValue("Funcraft Check", false);

    private final BoolValue ncps = new BoolValue("NPC Detection Check", false);

    private final BoolValue middleClick = new BoolValue("Middle Click Bot", false);*/

    @EventTarget
    public void onPreMotion(EventPreMotion event) {
        if (watchdogAntiBot.get()) {
            mc.theWorld.playerEntities.forEach(player -> {
                final NetworkPlayerInfo info = mc.getNetHandler().getPlayerInfo(player.getUniqueID());

                if (info == null) {
                    NekoCat.instance.botManager.add(player);
                } else {
                    NekoCat.instance.botManager.remove(player);
                }
            });
        }
    }

    @Override
    public void onDisable() {
        NekoCat.instance.botManager.clear();
    }
}
