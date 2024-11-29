package domain;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions; //거래 목록

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount));
        return balance;
    }

    public double withdraw(double amount) { //출금
        if (amount > balance) { //잔액보다 큰 금액 출금하는 경우
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction("withdraw", amount));
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: " + balance;
    }
}