package link.liycxc.api.events.impl;

import lombok.Getter;
import lombok.Setter;
import link.liycxc.api.events.Event;

/**
 * @author: Liycxc
 * @date: 2023-06-30
 * @time: 20:03
 */
@Setter
@Getter
public class EventSyncCurrentItem extends Event {
    private int slot;
    public EventSyncCurrentItem(int slot) {
        this.slot = slot;
    }
}