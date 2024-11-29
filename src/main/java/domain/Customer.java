package domain;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int age;
    private String password;
    private List<Account> accounts; //계좌 목록 관리

    public Customer(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Age: " + age;
    }
}