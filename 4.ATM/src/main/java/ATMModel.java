import java.io.IOException;

public class ATMModel {

    private static final ATMModel instance = new ATMModel();
    private ATMModel() {}
    public static ATMModel getInstance() {
        return instance;
    }

    AccountManager accountManager = new AccountManager("clients.txt");
    NoteManager noteManager = new NoteManager("notes.txt");
    User user = null;
    WithdrawStrategy withdrawStrategy;


    public User setUser(long cardNr, int pinNr) throws IOException {
        return accountManager.checkAccount(cardNr, pinNr);
    }

    public void logOutUser(){
        user = null;
    }

    public void withdraw(int value){

        withdrawStrategy = new WithdrawStrategy(user, accountManager);
        withdrawStrategy.withdrawMoney(value);
    }

}
