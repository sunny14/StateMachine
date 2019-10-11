package event.impl;

import event.Event;

public class EventImpl1 implements Event {

    @Override
    public EVENT_TYPE getType() {
        return EVENT_TYPE.ONE;
    }
}
