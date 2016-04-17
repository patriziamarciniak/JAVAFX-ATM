import org.junit.Assert;
import org.junit.Test;

public class NoteFileReaderTest {

    @Test
    public void shouldReadNotes(){

        NoteManager noteManager = new NoteManager( "notesTest.txt" );

        Integer result = 10;
        Assert.assertEquals(result, noteManager.notes.get(0));
        Assert.assertEquals(result, noteManager.notes.get(4));
    }
}
