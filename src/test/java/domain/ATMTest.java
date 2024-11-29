// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// public class ATMTest {

//     private Bank mockBank;
//     private Customer mockCustomer;
//     private Account mockAccount;
//     private ATM atm;

//     @BeforeEach
//     public void setUp() {
//         // Mockito를 사용하여 Mock 객체 생성
//         mockBank = mock(Bank.class);
//         mockCustomer = mock(Customer.class);
//         mockAccount = mock(Account.class);
//         atm = new ATM(mockBank);
//     }

//     @Test
//     public void testLogin_Success() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(mockCustomer);
//         when(mockCustomer.getPassword()).thenReturn("password123");

//         // 테스트 실행
//         boolean loginResult = atm.login("John", "password123");

//         // 검증
//         assertTrue(loginResult);
//         verify(mockBank, times(1)).findCustomer("John");
//         verify(mockCustomer, times(1)).getPassword();
//     }

//     @Test
//     public void testLogin_Failure() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(null);

//         // 테스트 실행
//         boolean loginResult = atm.login("John", "password123");

//         // 검증
//         assertFalse(loginResult);
//         verify(mockBank, times(1)).findCustomer("John");
//         verifyNoInteractions(mockCustomer); // Customer와의 상호작용 없음
//     }

//     @Test
//     public void testPerformTransaction_Deposit_Success() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(mockCustomer);
//         when(mockCustomer.getPassword()).thenReturn("password123");
//         when(mockCustomer.getAccounts()).thenReturn(List.of(mockAccount));
//         when(mockAccount.toString()).thenReturn("12345");

//         atm.login("John", "password123");

//         // 테스트 실행
//         when(mockAccount.getBalance()).thenReturn(1000.0, 1200.0); // 이전 및 새로운 잔액
//         String result = atm.performTransaction("deposit", "12345", 200.0);

//         // 검증
//         assertEquals("Deposited: $200.0\nNew Balance: $1200.0", result);
//         verify(mockAccount, times(1)).deposit(200.0);
//         verify(mockAccount, times(2)).getBalance();
//     }

//     @Test
//     public void testPerformTransaction_Withdraw_InsufficientFunds() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(mockCustomer);
//         when(mockCustomer.getPassword()).thenReturn("password123");
//         when(mockCustomer.getAccounts()).thenReturn(List.of(mockAccount));
//         when(mockAccount.toString()).thenReturn("12345");

//         atm.login("John", "password123");

//         // 테스트 실행
//         doThrow(new IllegalArgumentException("Insufficient funds")).when(mockAccount).withdraw(500.0);
//         String result = atm.performTransaction("withdraw", "12345", 500.0);

//         // 검증
//         assertEquals("Insufficient funds.", result);
//         verify(mockAccount, times(1)).withdraw(500.0);
//     }

//     @Test
//     public void testCheckBalance_Success() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(mockCustomer);
//         when(mockCustomer.getPassword()).thenReturn("password123");
//         when(mockCustomer.getAccounts()).thenReturn(List.of(mockAccount));
//         when(mockAccount.toString()).thenReturn("12345");
//         when(mockAccount.getBalance()).thenReturn(1000.0);

//         atm.login("John", "password123");

//         // 테스트 실행
//         String result = atm.checkBalance("12345");

//         // 검증
//         assertEquals("Current Balance: $1000.0", result);
//         verify(mockAccount, times(1)).getBalance();
//     }

//     @Test
//     public void testCheckBalance_AccountNotFound() {
//         // Mock 동작 설정
//         when(mockBank.findCustomer("John")).thenReturn(mockCustomer);
//         when(mockCustomer.getPassword()).thenReturn("password123");
//         when(mockCustomer.getAccounts()).thenReturn(List.of(mockAccount));
//         when(mockAccount.toString()).thenReturn("67890"); // 다른 계좌 번호

//         atm.login("John", "password123");

//         // 테스트 실행
//         String result = atm.checkBalance("12345");

//         // 검증
//         assertEquals("Account not found.", result);
//     }
// }
