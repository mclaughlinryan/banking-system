import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.lang.StringBuffer;

public abstract class Account {
    private final int accountNumber;
    private final String accountHolder;
    private float accountBalance;
    private StringBuffer dateOfLastTransaction = new StringBuffer();
    private List<Float> creditRecord = new ArrayList<Float>();
    private List<Float> debitRecord = new ArrayList<Float>();
    private List<Float> transactionRecord = new ArrayList<Float>();

    public Account(int accountNumber, String accountHolder, float accountBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public float getAccountBalance() { return accountBalance; }
    public String getDateOfLastTransaction() { return dateOfLastTransaction.toString(); }
    public List<Float> getCreditRecord() { return this.creditRecord; }
    public List<Float> getDebitRecord() { return this.debitRecord; }
    public List<Float> getTransactionRecord() { return this.transactionRecord; }

    public void setDateOfLastTransaction(String date) {
        this.dateOfLastTransaction.setLength(0);
        this.dateOfLastTransaction.append(date);
    }

    public void modifyAccountBalance(float transactionAmount) { this.accountBalance += transactionAmount; }
    public void addToDebitRecord(float debit) { this.debitRecord.add(debit); }
    public void addToCreditRecord(float credit) { this.creditRecord.add(credit); }
    public void addToTransactionRecord(float transactionAmount) { this.transactionRecord.add(transactionAmount); }

    public abstract void postCredit(float credit);
    public abstract void postDebit(float debit);
    public abstract void reportCredit();
    public abstract void reportDebit();
    public abstract void reportTransaction();
}
