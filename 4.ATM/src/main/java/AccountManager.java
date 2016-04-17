import java.io.BufferedReader;
import java.io.IOException;


public class AccountManager {

    BufferedReader reader;
    AccountsFileReader accountsFileReader;
    AccountsFileWriter accountsFileWriter;


    AccountManager(String pathToFile){

        this.accountsFileReader = new AccountsFileReader(pathToFile);
        this.accountsFileWriter = new AccountsFileWriter(pathToFile);
    }

    public User checkAccount(Long cardNr, int pinNr) throws IOException {

        try {
            reader = accountsFileReader.readFile();

            boolean found = false;
            String line = reader.readLine();
            User user = null;
            while (line != null) {

                String[] columns = line.split("-");
                if (cardNr == Long.parseLong(columns[1]) && Integer.parseInt(columns[2]) == pinNr) {

                    user = new User(Long.parseLong(columns[0]), Long.parseLong(columns[1]), Integer.parseInt(columns[2]), Integer.parseInt(columns[3]));
                    found = true;
                    reader.close();
                    return user;
                }
                line = reader.readLine();
            }
            reader.close();
            return user;

        }catch(IOException e){
            throw e;
        }

    }
}

