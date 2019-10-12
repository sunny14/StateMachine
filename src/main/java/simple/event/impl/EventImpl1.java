package simple.event.impl;

import simple.event.Event;

public class EventImpl1 implements Event {

    @Override
    public EVENT_TYPE getType() {
        return EVENT_TYPE.ONE;
    }
}
