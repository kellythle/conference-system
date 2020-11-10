package Gateway;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface for Gateways classes making sure they can read/write
 *
 * @author Filip Jovanovic, Chaolin Wang
 */
public interface ReadWrite {
    //Gets/accesses a container from a use case and serializes to the given file path
    void saveToFile(String writeFilepath) throws IOException;
    //Reads a container from writeFilepath and stores it to the respective use case that has it
    void readFromFile(String readFilepath) throws ClassNotFoundException;
}
