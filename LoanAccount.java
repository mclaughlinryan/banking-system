import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.lang.StringBuffer;

public class LoanAccount extends Account {
    public LoanAccount(int accountNumber, String accountHolder, float accountBalance) {
        super(accountNumber, accountHolder, accountBalance);
    }

    @Override
    public void postCredit(float credit) {
        this.modifyAccountBalance(credit);
        this.addToCreditRecord(credit);
        this.addToTransactionRecord(credit);
        this.setDateOfLastTransaction(java.time.LocalDate.now().toString());
    }

    @Override
    public void postDebit(float debit) {
        if (debit > this.getAccountBalance()) {
            // debit exceeds balance in the account
            System.out.println("The debit exceeds the available funds in this account. Thus it is not posted to the account.");
        }
        else {
            this.modifyAccountBalance(-debit);
            this.addToDebitRecord(debit);;
            this.addToTransactionRecord(-debit);
            this.setDateOfLastTransaction(java.time.LocalDate.now().toString());
        }
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
            System.out.printf("Debit #%d: (%.2f)%n", i, debitRecord.get(i));
        }
    }

    @Override
    public void reportTransaction() {

        System.out.println("Loan account transaction record:");

        List<Float> transactionRecord = this.getTransactionRecord();

        for (int i = 0; i < transactionRecord.size(); i++) {
            if (transactionRecord.get(i) < 0) {
                System.out.printf("Debit #%d: (%.2f)%n", i, -transactionRecord.get(i));
            }
            else {
                System.out.printf("Credit #%d: %.2f%n", i, transactionRecord.get(i));
            }
        }
    }
}
