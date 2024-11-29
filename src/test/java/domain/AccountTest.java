package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @Test
    void testDeposit() {
        // // 초기 잔액 확인
        // assertEquals(0.0, account.getBalance());

        // 입금 후 잔액 확인
        Account account = new Account("12345");

        double balance = account.deposit(100.0);

        assertEquals(100.0, balance);
        assertEquals(100.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
        assertEquals("deposit", account.getTransactions().get(0).getTransactionType());
        assertEquals(100.0, account.getTransactions().get(0).getAmount());
    }

    @Test
    void testWithdraw() {
        //셋업
        Account account = new Account("12345");

        // 실행
        account.deposit(100.0);
        double newBalance = account.withdraw(50.0);


        assertEquals(300.0, newBalance);
        assertEquals(300.0, account.getBalance());

        // 거래 내역 확인
        List<Transaction> transactions = account.getTransactions();
        assertEquals(2, transactions.size()); // 입금 + 출금
        assertEquals("withdraw", transactions.get(1).getType());
        assertEquals(200.0, transactions.get(1).getAmount());
    }

    @Test
    void testWithdrawWithInsufficientFunds() {
        //셋업
        Account account = new Account("12345");

        // 잔액 부족 시 예외 발생 확인
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.0));
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void testToString() {
        //셋업
        Account account = new Account("12345");
        
        account.deposit(1000.0);
        String expectedString = "Account Number: 12345, Balance: 1000.0";
        assertEquals(expectedString, account.toString());
    }

    @Test
    void testGetTransactions() {
        account.deposit(500.0);
        account.withdraw(200.0);

        // 거래 내역 크기 확인
        List<Transaction> transactions = account.getTransactions();
        assertEquals(2, transactions.size());

        // 입금 거래 확인
        assertEquals("deposit", transactions.get(0).getType());
        assertEquals(500.0, transactions.get(0).getAmount());

        // 출금 거래 확인
        assertEquals("withdraw", transactions.get(1).getType());
        assertEquals(200.0, transactions.get(1).getAmount());
    }
}
