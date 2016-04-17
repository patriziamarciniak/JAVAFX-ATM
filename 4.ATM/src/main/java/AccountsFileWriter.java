import java.io.*;


public class AccountsFileWriter {

    String path;

    AccountsFileWriter(String pathToFile){
        this.path = pathToFile;
    }


    public void writeUserToFile(User user) throws IOException {

        FileOutputStream writer;
        BufferedReader reader;
        AccountsFileReader accountsFileReader = new AccountsFileReader(path);

        reader = accountsFileReader.readFile();

        String line;
        String input = "";

        while ((line = reader.readLine()) != null) {
            if (!line.contains((user.cardNumber).toString())) {
                input += line + '\n';
            }
        }

        try {
            writer = new FileOutputStream("S:\\Budowa aplikacji w środowisku JAVA\\BitBucket\\budowa-aplikacji-w-rodowisku-java.git\\4.ATM\\build\\resources\\main\\" + path);
        } catch (IOException e) {
            throw e;
        }

        writer.write((user.accountNumber).toString().getBytes());
        writer.write("-".getBytes());
        writer.write((user.cardNumber).toString().getBytes());
        writer.write("-".getBytes());
        writer.write(Integer.toString(user.pin).getBytes());
        writer.write("-".getBytes());
        writer.write(Integer.toString(user.saldo).getBytes());
        writer.write("\n".getBytes());


        reader.close();
        writer.write(input.getBytes());

        writer.close();
    }
}
