import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AccountFileWriterTest {

    @Test
    public void shouldWriteUserToFile() throws IOException {

        User user = new User(1111L, 3333L, 2222, 5555);
        User userTest;
        AccountsFileWriter accountsFileWriter = new AccountsFileWriter("clientsTest2.txt");
        accountsFileWriter.writeUserToFile(user);

        AccountManager accountManager = new AccountManager("clientsTest2.txt");
        userTest = accountManager.checkAccount(3333L, 2222);
        Assert.assertEquals(user.saldo, userTest.saldo);

    }
}


