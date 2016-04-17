import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WithdrawStrategyTest {

    @Test
    public void shouldWithdrawMoney() throws IOException {

        User user = new User(5555L, 5555L, 5555, 5555);
        User userTest;
        AccountManager accountManager = new AccountManager("clientsTest2.txt");

        WithdrawStrategy withdrawStrategy = new WithdrawStrategy(user, accountManager);
        user = withdrawStrategy.withdrawMoney(2000);
        Assert.assertEquals(3555, user.saldo);

        userTest = accountManager.checkAccount(5555L, 5555);
        Assert.assertEquals(user.accountNumber, userTest.accountNumber);
    }
}
