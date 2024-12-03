package domain;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Customer> customers; //고객 목록 관리

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Bank: " + name + ", Customers: " + customers.size();
    }
}