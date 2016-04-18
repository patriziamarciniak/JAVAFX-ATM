import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ATMPresenter {

    private ATMView view;
    ATMModel atm = ATMModel.getInstance();

    public ATMPresenter(ATMView atmView) {
        this.view = atmView;
    }


    @FXML
    protected void buttonLoginClicked() {

        if ((view.getPIN() == "empty") || (view.getCardNumber() == "empty")) {

            view.showAlert("Błędny numer karty lub numer PIN  Spróbuj ponownie");
        } else {

            try {
                atm.user = atm.setUser(Long.parseLong(view.getCardNumber()), Integer.parseInt(view.getPIN()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (atm.user == null) {
                view.showAlert("Klient nie znajduje się w bazie danych banku");
            } else {
                view.setUserLogged();

            }
        }
    }

    @FXML
    protected void buttonLogOutClicked() {

        atm.logOutUser();
        view.setUserLogOut();

    }

    @FXML
    protected void buttonChooseNotesClicked() {

        List<Integer> notes = new ArrayList<Integer>();


        int suma = 0;

        notes.add(view.getNote(200));
        notes.add(view.getNote(100));
        notes.add(view.getNote(50));
        notes.add(view.getNote(20));
        notes.add(view.getNote(10));


        suma += notes.get(0) * 200;
        suma += notes.get(1) * 100;
        suma += notes.get(2) * 50;
        suma += notes.get(3) * 20;
        suma += notes.get(4) * 10;

        System.out.print(suma);

        try {
            atm.noteManager.readNotes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            atm.noteManager.notes.set(i, (atm.noteManager.notes.get(i) + notes.get(i)));
        }

        try {
            atm.noteManager.noteFileWriter.writeNotesToFile(atm.noteManager.notes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        atm.user.saldo += suma;

        try {
            atm.accountManager.accountsFileWriter.writeUserToFile(atm.user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.showAlert("Na konto wpłacono " + suma + " zł");
        view.goBackFromPaying();
    }

    @FXML
    protected void buttonWithdrawClicked() {

        if (view.getInputValue() == "empty") {
            view.showAlert("Nie podano kwoty");
        } else {
            int value = Integer.parseInt(view.getInputValue());
            if (value <= atm.user.saldo) {
                if (value <= atm.noteManager.moneyInATM) {
                    if (value % 10 == 0) {

                        atm.withdraw(value);
                        System.out.print(atm.user.saldo);

                        try {
                            atm.noteManager.getMoney(value);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        view.showAlert("Wypłacono " + value + " zł");


                    } else {
                        view.showAlert("Kwota nie jest wielokrotnością 10");
                    }
                } else {
                    view.showAlert("Brak środków w bankomacie, skontaktuj się z serwisem");
                }
            } else {
                view.showAlert("Nie posiadasz wystarczających środków na koncie");
            }
        }

    }

    @FXML
    protected void buttonFileOkClicked() {

        if (view.getFileName() == "empty") {
            view.showAlert("Nie podano nazwy pliku");
        } else {
            String fileName = view.getFileName();
            atm.accountManager = new AccountManager(fileName);
            view.setStartScene();
        }

    }

    @FXML
    protected void buttonNoteFileOkClicked(){

        if (view.getFileName() == "empty") {
            view.showAlert("Nie podano nazwy pliku");
        } else {
            String fileName = view.getFileName();
            atm.noteManager = new NoteManager(fileName);
            view.setStartScene();
        }
    }

    @FXML
    protected void buttonChangePINClicked(){

        int newPIN = view.getPIN(2);
        int oldPIN = view.getPIN(1);

        if (newPIN == 0 || oldPIN == 0 || newPIN < 1000 || newPIN > 9999 || oldPIN < 1000 || oldPIN > 9999) {
            view.showAlert("Błędny numer PIN");
        } else {
            if (oldPIN == atm.user.pin){
                atm.user.pin = newPIN;
                try {
                    atm.accountManager.accountsFileWriter.writeUserToFile(atm.user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.showAlert("Numer PIN zmieniony");
                view.setStartScene();
            }else{
                view.showAlert("Niezgodny stary numer PIN");
            }
        }
    }
}
