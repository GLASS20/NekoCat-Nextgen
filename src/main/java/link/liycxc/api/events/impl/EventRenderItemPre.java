package link.liycxc.api.events.impl;

import lombok.Getter;
import lombok.Setter;
import link.liycxc.api.events.Event;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

@Getter
@Setter
public class EventRenderItemPre extends Event {
    private EnumAction enumAction;
    private boolean useItem;
    private float animationProgression, partialTicks, swingProgress;
    private ItemStack itemToRender;
    public EventRenderItemPre(EnumAction enumAction, boolean useItem, float animationProgression, float partialTicks, float swingProgress, ItemStack itemToRender) {
        this.enumAction = enumAction;
        this.useItem = useItem;
        this.animationProgression = animationProgression;
        this.partialTicks = partialTicks;
        this.swingProgress = swingProgress;
        this.itemToRender = itemToRender;
    }
}
