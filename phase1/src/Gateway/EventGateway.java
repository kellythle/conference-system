package Gateway;

import Controller.ManagerFacade;
import Entity.Event;
import UseCase.EventManager;

import java.io.*;
import java.util.ArrayList;

/**
 * Deals with the reading and writing of Events
 *
 * @author Chaolin Wang
 */
public class EventGateway implements ReadWrite {
    private EventManager em;

    public EventGateway(ManagerFacade managerFacade) {
        this.em = managerFacade.getEventManager();
    }

    /**
     *
     * @param filePath The files path the method writes to
     * @throws IOException Cannot write to file
     */
    @Override
    public void saveToFile(String filePath) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the List of events
        ArrayList<Event> eventListToWrite = this.em.getEventList();
        output.writeObject(eventListToWrite);

        file.close();
        buffer.close();
        output.close();
    }

    /**
     * Reads in a file, deserializes it into an Arraylist of Events and stores it into the EventManager
     * @param readFilepath The files path the method reads from
     * @throws ClassNotFoundException If it cannot cast to the given class
     */
    @Override
    public void readFromFile(String readFilepath) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(readFilepath); // String path should be "fileName.ser"
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the eventList
            ArrayList<Event> eventListIn = (ArrayList) input.readObject();
            this.em.setEventList(eventListIn);

            file.close();
            buffer.close();
            input.close();
        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new StudentManager.", ex);
        }
    }
}
