import java.io.*;
import java.util.List;

public class NoteFileWriter {

    String path;

    NoteFileWriter(String pathToFile){
        this.path = pathToFile;
    }

    public void writeNotesToFile(List<Integer> notes)throws IOException{

        try{
        FileOutputStream writer = new FileOutputStream("S:\\Budowa aplikacji w środowisku JAVA\\BitBucket\\budowa-aplikacji-w-rodowisku-java.git\\4.ATM\\build\\resources\\main\\" + path);
            for (int i=0;i<5;i++) {
                writer.write(Integer.toString(notes.get(i)).getBytes());
                writer.write("\n".getBytes());
            }
            writer.close();
        } catch (IOException e) {
            throw e;
        }

    }
}
