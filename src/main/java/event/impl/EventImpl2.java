package event.impl;

import event.Event;

public class EventImpl2 implements Event {

    @Override
    public EVENT_TYPE getType() {
        return EVENT_TYPE.TWO;
    }
}
