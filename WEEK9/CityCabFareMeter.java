// Implement: City Cab Fare Meter
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface NightServiceable {
    double NIGHT_SURCHARGE_RATE = 0.20;

    default double applyNightSurcharge(double fare) {
        return fare * (1.0 + NIGHT_SURCHARGE_RATE);
    }
}

abstract class Cab {
    private static final double MINIMUM_FARE = 100.0;
    private final String type;

    public Cab(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract double getRatePerKm();

    public double calculateBaseFare(double distance) {
        double rawFare = distance * getRatePerKm();
        return Math.max(rawFare, MINIMUM_FARE);
    }
}

class MiniCab extends Cab {
    public MiniCab() {
        super("MINI");
    }

    @Override
    public double getRatePerKm() {
        return 10.0;
    }
}

class SedanCab extends Cab implements NightServiceable {
    public SedanCab() {
        super("SEDAN");
    }

    @Override
    public double getRatePerKm() {
        return 14.0;
    }
}

class SuvCab extends Cab implements NightServiceable {
    public SuvCab() {
        super("SUV");
    }

    @Override
    public double getRatePerKm() {
        return 18.0;
    }
}

class CabTrip {
    private final Cab cab;
    private final double distance;
    private final String time;

    public CabTrip(Cab cab, double distance, String time) {
        this.cab = cab;
        this.distance = distance;
        this.time = time;
    }

    public Cab getCab() {
        return cab;
    }

    public double getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }
}

public class CityCabFareMeter {

    public static Cab createCab(String type) {
        switch (type.trim().toUpperCase()) {
            case "MINI":
                return new MiniCab();
            case "SEDAN":
                return new SedanCab();
            case "SUV":
                return new SuvCab();
            default:
                throw new IllegalArgumentException("Unknown cab type: " + type);
        }
    }

    public static void processTrips(List<CabTrip> trips) {
        double grandTotal = 0.0;
        for (CabTrip trip : trips) {
            Cab cab = trip.getCab();
            boolean isNight = trip.getTime().equalsIgnoreCase("NIGHT");

            if (isNight) {
                if (cab instanceof NightServiceable) {
                    double baseFare = cab.calculateBaseFare(trip.getDistance());
                    double fare = ((NightServiceable) cab).applyNightSurcharge(baseFare);
                    grandTotal += fare;
                    System.out.printf(Locale.US, "%s: %.2f%n", cab.getType(), fare);
                } else {
                    System.out.printf("%s: night service not available%n", cab.getType());
                }
            } else {
                double fare = cab.calculateBaseFare(trip.getDistance());
                grandTotal += fare;
                System.out.printf(Locale.US, "%s: %.2f%n", cab.getType(), fare);
            }
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
        List<CabTrip> trips = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String cabType = scanner.next();
            double distance = scanner.nextDouble();
            String time = scanner.next();
            trips.add(new CabTrip(createCab(cabType), distance, time));
        }
        processTrips(trips);
        scanner.close();
    }
}
