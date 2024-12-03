package domain;

public class TransferTransaction extends Transaction {
    private String fromAccountNumber;
    private String toAccountNumber;

    public TransferTransaction(double amount, String fromAccountNumber, String toAccountNumber) {
        super("transfer", amount);
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
    }

    @Override
    public String toString() {
        return "Transfer Transaction: From Account " + fromAccountNumber + " To Account " + toAccountNumber + ", Amount: $" + getAmount();
    }
}
