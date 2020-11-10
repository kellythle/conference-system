package Controller;

import java.io.IOException;

public interface ReadWrite {

    void saveToFile(String writefilepath) throws IOException;

    <T> Class<T> readFromFile(String readFilepath, Class<T> container) throws ClassNotFoundException;

}
