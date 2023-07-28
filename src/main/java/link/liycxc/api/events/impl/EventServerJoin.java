package link.liycxc.api.events.impl;

import link.liycxc.api.events.Event;

public class EventServerJoin extends Event {
    public String ip;
    public int port;
    public EventServerJoin(String ip,int port) {
        this.ip = ip;
        this.port = port;
    }
}
