package state.impl;

import state.State;

public class StateMachine2 implements State {
    @Override
    public void next(Machine machine) {
        machine.setState(new StateMachine3());
    }

    @Override
    public void printStatus() {
        System.out.println("THIS HAPPEND! It's a 1-st time when 3 same-type events came one after another!!!");
    }
}
