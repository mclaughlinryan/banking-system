import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.lang.StringBuffer;

public class CheckingAccount extends Account {
    private final float overdraftLimit;

    public CheckingAccount(int accountNumber, String accountHolder, float accountBalance, float overdraftLimit) {
        super(accountNumber, accountHolder, accountBalance);

        this.overdraftLimit = overdraftLimit;
    }

    public float getOverdraftLimit() { return this.overdraftLimit; }

    @Override
    public void postCredit(float credit) {
        if (credit > (this.getAccountBalance() + this.overdraftLimit)) {
            // credit exceeds overdraft limit in the account
            System.out.println("The credit exceeds the available funds as well as the overdraft limit in the account, thus it cannot be paid. This credit has not been posted to the account.");
        }
        else {
            this.modifyAccountBalance(-credit);
            this.addToCreditRecord(credit);
            this.addToTransactionRecord(-credit);
            this.setDateOfLastTransaction(java.time.LocalDate.now().toString());
        }
    }

    @Override
    public void postDebit(float debit) {
        this.modifyAccountBalance(debit);
        this.addToDebitRecord(debit);
        this.addToTransactionRecord(debit);
        this.setDateOfLastTransaction(java.time.LocalDate.now().toString());
    }

    @Override
    public void reportCredit() {

        System.out.println("Loan account credit record:");

        List<Float> creditRecord = this.getCreditRecord();

        for (int i = 0; i < creditRecord.size(); i++) {
            System.out.printf("Credit #%d: (%.2f)%n", i, creditRecord.get(i));
        }
    }

    @Override
    public void reportDebit() {

        System.out.println("Loan account debit record:");

        List<Float> debitRecord = this.getDebitRecord();

        for (int i = 0; i < debitRecord.size(); i++) {
            System.out.printf("Debit #%d: %.2f%n", i, debitRecord.get(i));
        }
    }

    @Override
    public void reportTransaction() {

        System.out.println("Loan account transaction record:");

        List<Float> transactionRecord = this.getTransactionRecord();

        for (int i = 0; i < transactionRecord.size(); i++) {
            if (transactionRecord.get(i) < 0) {
                System.out.printf("Credit #%d: (%.2f)%n", i, -transactionRecord.get(i));
            }
            else {
                System.out.printf("Debit #%d: %.2f%n", i, transactionRecord.get(i));
            }
        }
    }
}
