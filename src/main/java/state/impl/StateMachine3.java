package state.impl;

import state.State;

public class StateMachine3 implements State {

    @Override
    public void next(Machine machine){

    }

    @Override
    public void printStatus() {
        System.out.println("this is a final state, nothing is going to happen");
    }
}
