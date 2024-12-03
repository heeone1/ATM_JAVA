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

    public void transfer(double amount, Account toAccount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }
        this.withdraw(amount); // 송신 계좌에서 금액 차감
        toAccount.deposit(amount); // 수신 계좌에 금액 추가
        this.transactions.add(new TransferTransaction(amount, this.accountNumber, toAccount.accountNumber));
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Balance: " + balance;
    }
}