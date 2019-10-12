import builder.MyBuilder;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;

public class BuilderTest {

    private StateMachine<MyBuilder.States, MyBuilder.Events> stateMachine = MyBuilder.getStateMachine();

    public BuilderTest() throws Exception {
    }


    @Test
    public void initialStateTest() {
        stateMachine.start();
        assert(stateMachine.getState().getId() == MyBuilder.States.INIT);
    }

}
