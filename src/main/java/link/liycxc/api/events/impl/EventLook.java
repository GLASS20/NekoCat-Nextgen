package link.liycxc.api.events.impl;

import link.liycxc.utils.vector.Vector2f;
import lombok.Getter;
import lombok.Setter;
import link.liycxc.api.events.Event;

public class EventLook extends Event {
    @Getter
    @Setter
    private Vector2f rotation;

    public EventLook(Vector2f rotation) {
        this.rotation = rotation;
    }
}
