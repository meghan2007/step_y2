import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Customer {
    private final String type;
    private final double amount;

    public Customer(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public abstract double calculateFinalAmount();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double amount) {
        super("STUDENT", amount);
    }

    @Override
    public double calculateFinalAmount() {
        return getAmount() * 0.90;
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double amount) {
        super("STAFF", amount);
    }

    @Override
    public double calculateFinalAmount() {
        return getAmount() * 0.95;
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double amount) {
        super("GUEST", amount);
    }

    @Override
    public double calculateFinalAmount() {
        return getAmount() + 10.0;
    }
}

public class CanteenBillingCounter {

    public static Customer createCustomer(String type, double amount) {
        switch (type.trim().toUpperCase()) {
            case "STUDENT":
                return new StudentCustomer(amount);
            case "STAFF":
                return new StaffCustomer(amount);
            case "GUEST":
                return new GuestCustomer(amount);
            default:
                throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }

    public static void processBills(List<Customer> bills) {
        double grandTotal = 0.0;
        for (Customer customer : bills) {
            double finalAmount = customer.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf(Locale.US, "%s: %.2f%n", customer.getType(), finalAmount);
        }
        System.out.printf(Locale.US, "Total: %.2f%n", grandTotal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        List<Customer> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            bills.add(createCustomer(type, amount));
        }
        processBills(bills);
        scanner.close();
    }
}
