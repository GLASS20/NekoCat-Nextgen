package link.liycxc.api.events.impl;

import lombok.Getter;
import lombok.Setter;
import link.liycxc.api.events.Event;

@Getter
@Setter
public class EventSlowDown extends Event {
    private float strafeMultiplier;
    private float forwardMultiplier;
    public EventSlowDown(float strafeMultiplier,float forwardMultiplier) {
        this.strafeMultiplier = strafeMultiplier;
        this.forwardMultiplier = forwardMultiplier;
    }
}
