package state;

import state.impl.Machine;

public interface State {

    void next(Machine e);


    void printStatus();

}
