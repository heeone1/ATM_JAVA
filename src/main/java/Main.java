import javax.swing.SwingUtilities;

import domain.ATM;
import domain.Account;
import domain.Bank;
import domain.Customer;
import presentation.ATMFrame;

public class Main {
    public static void main(String[] args) {
        // Create a bank
        Bank bank = new Bank("MyBank");

        // Create a customer
        Customer customer = new Customer("Alice", 30, "1111");
        Customer customer2 = new Customer("Hee", 25, "2222");
        bank.addCustomer(customer);
        bank.addCustomer(customer2);

        // Create an account for the customer
        Account account = new Account("11111111");
        Account account2 = new Account("22222222");
        customer.addAccount(account);
        customer2.addAccount(account2);

        // Create the ATM logic
        ATM atm = new ATM(bank);

        // Create and show the ATM GUI
        // GUI를 EDT(Event Dispatch Thread)에서 실행하도록 함
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMFrame(atm).setVisible(true); //atm = 컨트롤러
            }
        });
    }
}