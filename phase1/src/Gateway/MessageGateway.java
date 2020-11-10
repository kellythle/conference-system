package Gateway;

import Entity.Message;
import UseCase.MessageManager;

import java.io.*;
import java.util.HashMap;

public class MessageGateway {

    private MessageManager messageManager;

    public void saveToFile(String writeFilepath) throws IOException
    {
        OutputStream file = new FileOutputStream(writeFilepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // Get HashMap from UserManager
        // TODO: Need getter / setter for MessageManager systemMessages
        // HashMap<Integer, Message> messageHashMap = messageManager.systemMessages;

        // Write to file and close streams
        // output.writeObject(messageHashMap);
        output.close();
        buffer.close();
        file.close();
    }

    public void readFromFile(String readFilepath) throws ClassNotFoundException
    {
        try {
            InputStream file = new FileInputStream(readFilepath); // String path should be "fileName.ser"
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the HashMap
            // TODO: Need getter / setter for MessageManager systemMessages
            // messageManager.systemMessages = (HashMap) input.readObject();
            input.close();
            buffer.close();
            file.close();

        } catch (IOException ex) {
            // TODO: Need getter / setter for MessageManager systemMessages
            // messageManager.systemMessages = new HashMap<Integer, Message>();
        }
    }
}
