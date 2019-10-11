package event;

public interface Event {

    public enum EVENT_TYPE  {ONE, TWO}

    EVENT_TYPE getType();

}
