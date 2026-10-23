// Implement: Parcel Shipping Desk
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface Insurable {
    double calculateInsurance();
}

abstract class Parcel {
    private final String type;
    private final double weight;
    private final double declaredValue;

    public Parcel(String type, double weight, double declaredValue) {
        this.type = type;
        this.weight = weight;
        this.declaredValue = declaredValue;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public double getDeclaredValue() {
        return declaredValue;
    }

    public abstract double calculateCharge();
}

class StandardParcel extends Parcel {
    public StandardParcel(double weight, double declaredValue) {
        super("STANDARD", weight, declaredValue);
    }

    protected StandardParcel(String type, double weight, double declaredValue) {
        super(type, weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        return 40.0 + 10.0 * getWeight();
    }
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weight, double declaredValue) {
        super("EXPRESS", weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        return 80.0 + 15.0 * getWeight();
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * getDeclaredValue();
    }
}

class FragileParcel extends StandardParcel implements Insurable {
    private static final double HANDLING_FEE = 50.0;

    public FragileParcel(double weight, double declaredValue) {
        super("FRAGILE", weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        return super.calculateCharge() + HANDLING_FEE;
    }

    @Override
    public double calculateInsurance() {
        return 0.02 * getDeclaredValue();
    }
}

public class ParcelShippingDesk {

    public static Parcel createParcel(String type, double weight, double declaredValue) {
        switch (type.trim().toUpperCase()) {
            case "STANDARD":
                return new StandardParcel(weight, declaredValue);
            case "EXPRESS":
                return new ExpressParcel(weight, declaredValue);
            case "FRAGILE":
                return new FragileParcel(weight, declaredValue);
            default:
                throw new IllegalArgumentException("Unknown parcel type: " + type);
        }
    }

    public static void processParcels(List<Parcel> parcels) {
        double grandTotal = 0.0;
        for (Parcel parcel : parcels) {
            double charge = parcel.calculateCharge();
            double insurance = (parcel instanceof Insurable) ? ((Insurable) parcel).calculateInsurance() : 0.0;
            double total = charge + insurance;
            grandTotal += total;
            System.out.printf(Locale.US, "%s: Charge=%.2f Insurance=%.2f Total=%.2f%n",
                    parcel.getType(), charge, insurance, total);
        }
        System.out.printf(Locale.US, "Grand Total: %.2f%n", grandTotal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        List<Parcel> parcels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double weight = scanner.nextDouble();
            double declaredValue = scanner.nextDouble();
            parcels.add(createParcel(type, weight, declaredValue));
        }
        processParcels(parcels);
        scanner.close();
    }
}
