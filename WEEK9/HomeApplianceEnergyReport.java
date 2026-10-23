// Implement: Home Appliance Energy Report
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface SaverMode {
    double SAVER_DISCOUNT = 0.25;

    default double applySaverReduction(double units) {
        return units * (1.0 - SAVER_DISCOUNT);
    }
}

abstract class Appliance {
    private static final double COST_PER_UNIT = 8.0;
    private final String name;
    private final double powerRatingWatts;

    public Appliance(String name, double powerRatingWatts) {
        this.name = name;
        this.powerRatingWatts = powerRatingWatts;
    }

    public String getName() {
        return name;
    }

    public double getPowerRatingWatts() {
        return powerRatingWatts;
    }

    public double calculateStandardUnits(double hours) {
        return (powerRatingWatts * hours) / 1000.0;
    }

    public double calculateCost(double units) {
        return units * COST_PER_UNIT;
    }
}

class Fridge extends Appliance {
    public Fridge() {
        super("FRIDGE", 150.0);
    }
}

class AirConditioner extends Appliance implements SaverMode {
    public AirConditioner() {
        super("AC", 1500.0);
    }
}

class Television extends Appliance {
    public Television() {
        super("TV", 100.0);
    }
}

class WashingMachine extends Appliance implements SaverMode {
    public WashingMachine() {
        super("WASHER", 500.0);
    }
}

class ApplianceUsage {
    private final Appliance appliance;
    private final double hours;
    private final boolean saverRequested;

    public ApplianceUsage(Appliance appliance, double hours, boolean saverRequested) {
        this.appliance = appliance;
        this.hours = hours;
        this.saverRequested = saverRequested;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public double getHours() {
        return hours;
    }

    public boolean isSaverRequested() {
        return saverRequested;
    }
}

public class HomeApplianceEnergyReport {

    public static Appliance createAppliance(String type) {
        switch (type.trim().toUpperCase()) {
            case "FRIDGE":
                return new Fridge();
            case "AC":
                return new AirConditioner();
            case "TV":
                return new Television();
            case "WASHER":
                return new WashingMachine();
            default:
                throw new IllegalArgumentException("Unknown appliance type: " + type);
        }
    }

    public static void generateReport(List<ApplianceUsage> usages) {
        double totalCost = 0.0;
        for (ApplianceUsage usage : usages) {
            Appliance appliance = usage.getAppliance();
            if (usage.isSaverRequested()) {
                if (appliance instanceof SaverMode) {
                    double standardUnits = appliance.calculateStandardUnits(usage.getHours());
                    double units = ((SaverMode) appliance).applySaverReduction(standardUnits);
                    double cost = appliance.calculateCost(units);
                    totalCost += cost;
                    System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n", appliance.getName(), units, cost);
                } else {
                    System.out.printf("%s: saver mode not supported%n", appliance.getName());
                }
            } else {
                double units = appliance.calculateStandardUnits(usage.getHours());
                double cost = appliance.calculateCost(units);
                totalCost += cost;
                System.out.printf(Locale.US, "%s: Units=%.2f Cost=%.2f%n", appliance.getName(), units, cost);
            }
        }
        System.out.printf(Locale.US, "Total Cost: %.2f%n", totalCost);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        List<ApplianceUsage> usages = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextLine()) {
                break;
            }
            String line = scanner.nextLine().trim();
            while (line.isEmpty() && scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
            }
            if (line.isEmpty()) {
                break;
            }
            String[] parts = line.split("\\s+");
            String applianceType = parts[0];
            double hours = Double.parseDouble(parts[1]);
            boolean saverRequested = (parts.length >= 3 && parts[2].equalsIgnoreCase("SAVER"));
            usages.add(new ApplianceUsage(createAppliance(applianceType), hours, saverRequested));
        }
        generateReport(usages);
        scanner.close();
    }
}
