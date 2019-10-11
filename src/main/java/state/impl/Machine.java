package state.impl;

import event.Event;
import state.State;

public class Machine {

    public static final int LIMIT = 3;
    private static final int NOT_AVAILABLE = -1;

   //TODO: @Autowired
    private State state = new StateMachine1();

    private int sameEventsCounter = 0;
    private Event.EVENT_TYPE lastEventType = null;

    public void processEvent(Event e)   {

        if (sameEventsCounter == NOT_AVAILABLE )    {
            state.next(this);
            return;
        }
        if (lastEventType == null || e.getType() == lastEventType ) {

            sameEventsCounter++;

            if (sameEventsCounter == LIMIT) {
                state.next(this);
                sameEventsCounter = NOT_AVAILABLE;
            }
        }
        else {  //update new event type

            sameEventsCounter = 0;
        }

        lastEventType = e.getType();

    }


    void setState(State state)   {
        this.state = state;
    }

    public String getState() {
        return this.state.getClass().getName();
    }

    public void printStatus() {
        state.printStatus();
    }



}
