package builder;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

public class MyBuilder {

    public static void main(String[] args) throws Exception {
        StateMachine<String, String> machine = getStateMachine();
        machine.start();

        System.out.println("On the start: "+machine.getState().getId());

        for (int i=0; i<3; i++) {
            machine.sendEvent("E1");
            System.out.println("After sending E1 "+i+" times: "+machine.getState().getId());
        }

        machine.sendEvent("E1");

        System.out.println("After sending one more E1: "+machine.getState());

    }

    public static StateMachine<String, String> getStateMachine() throws Exception {
        StateMachineBuilder.Builder<String, String> builder
                = StateMachineBuilder.builder();
        builder.configureStates().withStates()
                .initial("SI")
                .state("S1")
                .end("SF");

        builder.configureTransitions()
                .withExternal()
                .source("SI").target("S1").event("E1").event("E1").event("E1")
                .and().withExternal()
                .source("S1").target("SF").event("E2");

        return builder.build();
    }

   /* enum States {
        Red, RedYellow, Green, Yellow
    }*/

}
