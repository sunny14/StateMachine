package spring.builder.serialization;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.io.Serializable;
import java.util.Map;

public  class SerializableStateMachineContext<S,E> implements Serializable {

    private S state;
    private E event;
    private Map eventHeaders;
    private SerializableExtendedState exState;

    public SerializableStateMachineContext(S state, E event, Map eventHeaders, ExtendedState extendedState) {
        this.state = state;
        this.event = event;
        this.eventHeaders = eventHeaders;
        this.exState = new SerializableExtendedState(extendedState);
    }

    public StateMachineContext<S, E> getContext() {
        return new DefaultStateMachineContext<S,E>(this.state, this.event, this.eventHeaders, this.exState);
    }
}

