package state.impl;

import state.State;

public class StateMachine1 implements State {
    @Override
    public void next(Machine machine) {
        machine.setState(new StateMachine2());
    }

    @Override
    public void printStatus() {
        System.out.println("nothing happen yet");
    }
}
