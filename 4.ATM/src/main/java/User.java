
public class User {

    int pin;
    int saldo;
    Long accountNumber;
    Long cardNumber;

    User(Long accountNr, Long cardNr, int pinNr, int saldoState){

        this.pin = pinNr;
        this.accountNumber = accountNr;
        this.cardNumber = cardNr;
        this.saldo = saldoState;
    }

}
