package domain;
public class Transaction {
    private String transactionType;
    private double amount;

    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction: " + transactionType + ", Amount: " + amount;
    }
}