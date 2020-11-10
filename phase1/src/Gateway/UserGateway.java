package Gateway;

import Entity.UserAccount;
import UseCase.UserManager;
import java.io.*;
import java.util.HashMap;

public class UserGateway implements ReadWrite {

    private UserManager userManager;

    public void saveToFile(String writeFilepath) throws IOException
    {
        OutputStream file = new FileOutputStream(writeFilepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // Get HashMap from UserManager
        HashMap<String, UserAccount> userHashMap = userManager.userMap;

        // Write to file and close streams
        output.writeObject(userHashMap);
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
            userManager.userMap = (HashMap) input.readObject();
            input.close();
            buffer.close();
            file.close();

        } catch (IOException ex) {
            userManager.userMap = new HashMap<String, UserAccount>();
        }
    }
}

