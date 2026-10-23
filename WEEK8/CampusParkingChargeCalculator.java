import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Vehicle {
    private final String type;
    private final int hours;

    public Vehicle(String type, int hours) {
        this.type = type;
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public int getHours() {
        return hours;
    }

    public abstract double calculateCharge();
}

class Bike extends Vehicle {
    public Bike(int hours) {
        super("BIKE", hours);
    }

    @Override
    public double calculateCharge() {
        return getHours() * 10.0;
    }
}

class Car extends Vehicle {
    public Car(int hours) {
        super("CAR", hours);
    }

    @Override
    public double calculateCharge() {
        if (getHours() <= 1) {
            return 30.0;
        }
        return 30.0 + (getHours() - 1) * 20.0;
    }
}

class Truck extends Vehicle {
    public Truck(int hours) {
        super("TRUCK", hours);
    }

    @Override
    public double calculateCharge() {
        double charge = getHours() * 50.0;
        return Math.max(100.0, charge);
    }
}

public class CampusParkingChargeCalculator {

    public static Vehicle createVehicle(String type, int hours) {
        switch (type.trim().toUpperCase()) {
            case "BIKE":
                return new Bike(hours);
            case "CAR":
                return new Car(hours);
            case "TRUCK":
                return new Truck(hours);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }

    public static void processVehicles(List<Vehicle> vehicles) {
        double grandTotal = 0.0;
        for (Vehicle vehicle : vehicles) {
            double charge = vehicle.calculateCharge();
            grandTotal += charge;
            System.out.printf(Locale.US, "%s: %.2f%n", vehicle.getType(), charge);
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
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int hours = scanner.nextInt();
            vehicles.add(createVehicle(type, hours));
        }
        processVehicles(vehicles);
        scanner.close();
    }
}
