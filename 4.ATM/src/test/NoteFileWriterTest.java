import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteFileWriterTest {

    @Test
    public void shouldWriteNotesToFile() throws IOException {

        NoteFileWriter noteFileWriter = new NoteFileWriter("notesTest3.txt");
        List<Integer> notes2 = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++){
                notes2.add(5);
        }
        noteFileWriter.writeNotesToFile(notes2);

        NoteManager noteManager = new NoteManager("notesTest3.txt");
        Assert.assertEquals(notes2.get(0), noteManager.notes.get(0));
        for (int i = 0; i < 5; i++){
            notes2.set(i, 2);
        }
        noteFileWriter.writeNotesToFile(notes2);
    }
}
