package Gateway;

import java.io.IOException;

public interface ReadWrite {

    void saveToFile(String writeFilepath) throws IOException;

    <T> Class<T> readFromFile(String readFilepath, Class<T> container) throws ClassNotFoundException;

}
