package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Денис on 18.04.2016.
 */
public interface Reader {

    void readFile(File file)  throws IOException;


}
