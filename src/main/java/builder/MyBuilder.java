package builder;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.Arrays;
import java.util.HashSet;

public class MyBuilder {

    public static void main(String[] args) throws Exception {
        StateMachine<States, Events> machine = getStateMachine();
        machine.start();

        System.out.println("On the start: "+machine.getState().getId());

        for (int i=1; i<=3; i++) {
            machine.sendEvent(Events.E1);
            System.out.println("After sending E1 "+i+" times: "+machine.getState().getId());
        }

        machine.sendEvent(Events.E1);

        System.out.println("After sending one more E1: "+machine.getState().getId());

    }

    static Action <States, Events> logAction() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("THIS HAPPEND! It's a 1-st time when 3 same-type events came one after another!!!");
            }
        };
    }

    public static StateMachine<States, Events> getStateMachine() throws Exception {
        StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();

        builder.configureStates().withStates()
                .initial(States.INIT)
                .states(new HashSet<>(Arrays.asList(States.values())))
                .end(States.FINAL);


        builder.configureTransitions()
                .withExternal().source(States.INIT).target(States.E1_1).event(Events.E1)
                .and()
                .withExternal().source(States.E1_1).target(States.E1_2).event(Events.E1)
                .and()
                .withExternal().source(States.E1_1).target(States.INIT).event(Events.E2)
                .and()
                .withExternal().source(States.E1_2).target(States.FINAL).event(Events.E1).action(logAction())
                .and()
                .withExternal().source(States.E1_2).target(States.INIT).event(Events.E2)
                .and()
                .withExternal().source(States.INIT).target(States.E2_1).event(Events.E2)
                .and()
                .withExternal().source(States.E2_1).target(States.E2_2).event(Events.E2)
                .and()
                .withExternal().source(States.E2_1).target(States.INIT).event(Events.E1)
                .and()
                .withExternal().source(States.E2_2).target(States.FINAL).event(Events.E2).action(logAction());

        return builder.build();
    }

    public enum States {
        INIT, E1_1, E1_2, E2_1, E2_2, FINAL
    }

    public enum Events {
        E1, E2
    }

}
