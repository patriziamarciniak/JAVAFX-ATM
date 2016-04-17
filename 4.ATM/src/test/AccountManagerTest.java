import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AccountManagerTest {

    @Test
    public void shouldFindUserInFile() throws IOException {

        User userPatern = new User(2222L, 3333L, 4444, 5555);
        User userTest;
        AccountManager accountManager = new AccountManager("clientsTest1.txt");
        userTest = accountManager.checkAccount(3333L, 4444);
        Assert.assertEquals(userPatern.saldo, userTest.saldo);
    }
}
