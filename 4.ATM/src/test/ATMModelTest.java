import org.junit.Assert;
import org.junit.Test;

public class ATMModelTest {

    @Test
    public void shouldSetUserNull()
    {
        ATMModel atm = ATMModel.getInstance();
        atm.user = new User(1111L, 2222L, 2222, 5555);
        atm.logOutUser();

        Assert.assertEquals(null, atm.user);
    }
}
