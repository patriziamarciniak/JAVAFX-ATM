import java.io.IOException;

public class WithdrawStrategy{

    AccountManager accountManager;
    User user;

    public WithdrawStrategy(User userObj, AccountManager accountManagerObj){

        this.user = userObj;
        this.accountManager = accountManagerObj;

    }

    public User withdrawMoney(int value){

        user.saldo = user.saldo - value;

        try {
            accountManager.accountsFileWriter.writeUserToFile(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;

    }

}
