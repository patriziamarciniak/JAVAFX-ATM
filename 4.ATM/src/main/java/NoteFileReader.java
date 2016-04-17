import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class NoteFileReader {

    String path;

    NoteFileReader(String pathToFile) {
        this.path = pathToFile;
    }

    public BufferedReader readFile() throws IOException {

            InputStream inputStream = ATMModel.class.getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader;




    }
}
