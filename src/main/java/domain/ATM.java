package domain;

public class ATM {
    private Bank bank;
    private Customer currentCustomer;
    private Account currentAccount;

    public ATM(Bank bank) {
        this.bank = bank;
    }

    public boolean login(String name, String password) {
        currentCustomer = bank.findCustomer(name);
        if (currentCustomer == null || !currentCustomer.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public String performTransaction(String type, String accountNumber, double amount) {
        if (currentCustomer == null) {
            return "Please login first.";
        }

        for (Account account : currentCustomer.getAccounts()) {
            if (account.toString().contains(accountNumber)) {
                currentAccount = account;
                break;
            }
        }

        if (currentAccount == null) {
            return "Account not found.";
        }

        if (type.equals("deposit")) {
            currentAccount.deposit(amount);
            return "Deposited: $" + amount + "\nNew Balance: $" + currentAccount.getBalance();
        } else if (type.equals("withdraw")) {
            try {
                currentAccount.withdraw(amount);
                return "Withdrew: $" + amount + "\nNew Balance: $" + currentAccount.getBalance();
            } catch (IllegalArgumentException ex) {
                return "Insufficient funds.";
            }
        }
        return "Invalid transaction type.";
    }

    public String checkBalance(String accountNumber) {
        if (currentCustomer == null) {
            return "Please login first.";
        }

        for (Account account : currentCustomer.getAccounts()) {
            if (account.toString().contains(accountNumber)) {
                currentAccount = account;
                break;
            }
        }

        if (currentAccount == null) {
            return "Account not found.";
        }

        return "Current Balance: $" + currentAccount.getBalance();
    }
}