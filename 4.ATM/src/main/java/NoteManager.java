import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteManager  {

    List<Integer> notes = new ArrayList<Integer>();
    NoteFileReader noteFileReader;
    NoteFileWriter noteFileWriter;
    BufferedReader reader;
    int moneyInATM;

    NoteManager(String pathToFile) {

        this.noteFileReader = new NoteFileReader(pathToFile);
        this.noteFileWriter = new NoteFileWriter(pathToFile);

        try {
            this.readNotes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readNotes() throws IOException{

        try {
            reader = noteFileReader.readFile();
            String line = reader.readLine();
            while (line != null) {
                notes.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            throw e;
        }
        countMoney();
    }

    public void countMoney(){
        moneyInATM = 0;
        moneyInATM += notes.get(0)*200;
        moneyInATM += notes.get(1)*100;
        moneyInATM += notes.get(2)*50;
        moneyInATM += notes.get(3)*20;
        moneyInATM += notes.get(4)*10;

    }

    public void getMoney( int value ) throws IOException{

        notes.set(0,notes.get(0)-value/200);
        value=value%200;
        notes.set(1,notes.get(1)-value/100);
        value=value%100;
        notes.set(2,notes.get(2)-value/50);
        value=value%50;
        notes.set(3,notes.get(3)-value/20);
        value=value%20;
        notes.set(4,notes.get(4)-value/10);

        noteFileWriter.writeNotesToFile(notes);

    }

}
