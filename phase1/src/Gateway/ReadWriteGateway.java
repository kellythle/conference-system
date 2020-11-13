package Gateway;

import java.io.*;

/**
 * Provides methods for serializing and deserializing objects from files.
 *
 * @author Filip Jovanovic, Chaolin Wang
 */
public class ReadWriteGateway implements ReadWrite {

    /**
     * Deserializes object from a file.
     *
     * @param filepath The files path the method reads from
     * @return Deserialized object as type Object.
     * @throws ClassNotFoundException If class of read object does not exist
     */
    @Override
    public Object readFromFile(String filepath) throws ClassNotFoundException {
        try
        {
            InputStream file = new FileInputStream(filepath);
            ObjectInput input = new ObjectInputStream(file);
            return input.readObject();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    /**
     * Serializes object to a file.
     *
     * @param filepath The file path to write to
     * @param data The object to serialize to the file
     * @throws IOException Writing to file was unsuccessful
     */
    @Override
    public void saveToFile(String filepath, Object data) throws IOException {
        OutputStream file = new FileOutputStream(filepath);
        ObjectOutput output = new ObjectOutputStream(file);

        output.writeObject(data);

        output.close();
        file.close();
    }


}
