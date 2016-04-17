import java.io.*;


public class AccountsFileReader {

    String path;

    AccountsFileReader(String pathToFile) {
        this.path = pathToFile;
    }


    public BufferedReader readFile(){

            InputStream inputStream = ATMModel.class.getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            return reader;
    }
}