package link.liycxc.api.events.impl;

import lombok.Getter;
import lombok.Setter;
import link.liycxc.api.events.Event;

@Getter
@Setter
public class EventMouseOver extends Event {
    private double range;
    private float expand;

    public EventMouseOver(double range, float expand) {
        this.range = range;
        this.expand = expand;
    }
}
