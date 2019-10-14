package builder;

import builder.serialization.SerializableStateMachineContext;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class FilePersister implements StateMachinePersist<MyBuilder.States, MyBuilder.Events, UUID> {

    private HashMap<UUID, SerializableStateMachineContext<MyBuilder.States, MyBuilder.Events>> storage = new HashMap<>();

    //TODO: get file name from properties file
    private String storageFile = "storage";

    @Override
    public void write(StateMachineContext<MyBuilder.States, MyBuilder.Events> context,
                      UUID contextObj) {

        storage.put(contextObj,
                new SerializableStateMachineContext(context.getState(), context.getEvent(), context.getEventHeaders(), context.getExtendedState()));
        //store to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(storageFile)))) {
            oos.writeObject(storage);
            System.out.println("state machine was stored at file "+storageFile);
        }catch (IOException ex) {
            //TODO: use logger.error
            System.out.println("failed to store state machine!");
        }
    }

    @Override
    public StateMachineContext<MyBuilder.States, MyBuilder.Events> read(UUID contextObj) throws Exception {
        //restore from file
        HashMap<UUID, SerializableStateMachineContext<MyBuilder.States, MyBuilder.Events>>  restoredContext = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(storageFile)))) {
            restoredContext = (HashMap<UUID, SerializableStateMachineContext<MyBuilder.States, MyBuilder.Events>>)ois.readObject();
        }catch (IOException ex) {
            //TODO: use logger.error
            System.out.println("failed to REstore state machine!");
            throw ex;
        }

        System.out.println("state machine was REstored from file \""+storageFile+"\"");
        return restoredContext.get(contextObj).getContext();
    }
}