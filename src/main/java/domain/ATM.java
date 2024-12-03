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


    public String performTransfer(String fromAccountNumber, String toAccountNumber, double amount) {
        
        if (currentCustomer == null) {
            return "Please login first.";
        }
    
        Account fromAccount = null;
        Account toAccount = null;
        Customer sender = null;
        Customer receiver = null;


        // 로그인한 사용자의 계좌에서 송신 계좌 확인
        for (Account account : currentCustomer.getAccounts()) {
            if (account.toString().contains(fromAccountNumber)) {
                fromAccount = account;
                break;
            }
        }

        if (fromAccount == null) {
            return "You do not own the sender account."; // 본인의 계좌가 아닌 경우
        }

        // 수신 계좌 찾기
        for (Customer customer : bank.getCustomers()) {
            for (Account account : customer.getAccounts()) {
                if (account.toString().contains(toAccountNumber)) {
                    toAccount = account;
                    receiver = customer;
                    break;
                }
            }
        }

        if (toAccount == null) {
            return "Receiver account not found.";
        }
        
        try {
            fromAccount.transfer(amount, toAccount);
            return "Transfer successful\n" +
               "Sender: " + currentCustomer.getName() + " (Account: " + fromAccountNumber + ")\n" +
               "Receiver: " + receiver.getName() + " (Account: " + toAccountNumber + ")\n" +
               "Amount: $" + amount;
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }
    }
    
}