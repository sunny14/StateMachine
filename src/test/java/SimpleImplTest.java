import simple.event.impl.EventImpl1;
import simple.event.impl.EventImpl2;
import org.junit.Test;
import simple.state.impl.StateMachine1;
import simple.state.impl.Machine;
import simple.state.impl.StateMachine2;
import simple.state.impl.StateMachine3;


public class SimpleImplTest {

    private final String initialiState = StateMachine1.class.getName();
    private final String intermediateState = StateMachine2.class.getName();
    private final String finalState = StateMachine3.class.getName();

    @Test
    public void initialStateTest()  {
        Machine m = new Machine();
        assert m.getState().equals(initialiState);

    }

    @Test
    public void noInterestingEventTest()   {

        Machine m = new Machine();

        m.processEvent(new EventImpl1());
        assert m.getState().equals(initialiState);
        m.processEvent(new EventImpl1());
        assert m.getState().equals(initialiState);
        m.processEvent(new EventImpl2());
        assert m.getState().equals(initialiState);

    }

    @Test
    public void interestingEventTest()  {
        Machine m = new Machine();

        for (int i=0; i < Machine.LIMIT; i++)   {
            m.processEvent(new EventImpl1());
        }

        assert m.getState().equals(intermediateState);
    }

    @Test
    public void finalStateTest() {
        Machine m = new Machine();

        for (int i=0; i < Machine.LIMIT+1; i++)   {
            m.processEvent(new EventImpl1());
        }

        assert m.getState().equals(finalState);
    }
}
