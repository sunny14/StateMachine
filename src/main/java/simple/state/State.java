package simple.state;

import simple.state.impl.Machine;

public interface State {

    void next(Machine e);


    void printStatus();

}
