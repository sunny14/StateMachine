import spring.builder.MyBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;

public class BuilderTest {

    private StateMachine<MyBuilder.States, MyBuilder.Events> stateMachine = MyBuilder.createStateMachine();

    public BuilderTest() throws Exception {
    }

    @Before
    public void init()  {
        stateMachine.start();
    }


    @Test
    public void initialStateTest() {
        assert(stateMachine.getState().getId() == MyBuilder.States.INIT);
    }

    @Test
    public void e1e2Test()  {
        stateMachine.sendEvent(MyBuilder.Events.E1);
        assert (stateMachine.getState().getId() == MyBuilder.States.E1_1);

        stateMachine.sendEvent(MyBuilder.Events.E2);
        assert (stateMachine.getState().getId() == MyBuilder.States.INIT);
    }

    @Test
    public void e1threeTimesTest()    {
        for(int i=0; i<3; i++)  {
            stateMachine.sendEvent(MyBuilder.Events.E1);
        }

        assert (stateMachine.getState().getId() == MyBuilder.States.FINAL);
    }

}
