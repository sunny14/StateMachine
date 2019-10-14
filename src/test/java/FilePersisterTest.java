import builder.FilePersister;
import builder.MyBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import java.util.UUID;
import static junit.framework.TestCase.assertTrue;


public class FilePersisterTest {

    private StateMachinePersister<MyBuilder.States, MyBuilder.Events, UUID> filePersist =
            new DefaultStateMachinePersister<>(new FilePersister());

    @Test
    public void testPersist() throws Exception {
        // setup
        StateMachine<MyBuilder.States, MyBuilder.Events> sm =
                MyBuilder.createStateMachine();
        sm.start();
        assertTrue(sm.getState().getId()== MyBuilder.States.INIT);
        sm.sendEvent(MyBuilder.Events.E1);
        assertTrue(sm.getState().getId()== MyBuilder.States.E1_1);

        // store state machine
        UUID machineId = sm.getUuid();
        filePersist.persist(sm, machineId);

        //create new machine
        sm = MyBuilder.createStateMachine();
        sm.start();
        assertTrue(sm.getState().getId()== MyBuilder.States.INIT);

        //restore state machine
        filePersist.restore(sm, machineId);
        assertTrue(sm.getState().getId()== MyBuilder.States.E1_1);

    }


}
