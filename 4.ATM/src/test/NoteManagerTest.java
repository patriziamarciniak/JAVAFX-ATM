import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016-04-17.
 */
public class NoteManagerTest {


    @Test
    public void shouldCountSumOfNotes(){

        NoteManager noteManager = new NoteManager( "notesTest.txt" );
        noteManager.countMoney();
        Assert.assertEquals(3800, noteManager.moneyInATM);
    }

    @Test
    public void shouldGetMoneyFromFile() throws IOException {

        NoteManager noteManager = new NoteManager( "notesTest2.txt" );
        Integer result = 0;
        Integer result2 = 1;
        noteManager.getMoney(300);
        Assert.assertEquals(result, noteManager.notes.get(0));
        Assert.assertEquals(result, noteManager.notes.get(1));
        Assert.assertEquals(result2, noteManager.notes.get(2));
        Assert.assertEquals(result2, noteManager.notes.get(3));
        Assert.assertEquals(result2, noteManager.notes.get(4));

        List<Integer> notes2 = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++){
            notes2.add(1);
        }
        noteManager.noteFileWriter.writeNotesToFile(notes2);

    }
}
