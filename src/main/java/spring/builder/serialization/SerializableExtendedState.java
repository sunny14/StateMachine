package spring.builder.serialization;

import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.support.DefaultExtendedState;
import java.io.Serializable;

public class SerializableExtendedState extends DefaultExtendedState implements Serializable {

    public SerializableExtendedState(ExtendedState extendedState) {
        super(extendedState.getVariables());
    }

}


