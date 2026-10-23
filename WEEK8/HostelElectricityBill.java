import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Room {
    private final String type;
    private final int units;

    public Room(String type, int units) {
        this.type = type;
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public int getUnits() {
        return units;
    }

    public abstract double calculateBill();
}

class SingleRoom extends Room {
    public SingleRoom(int units) {
        super("SINGLE", units);
    }

    @Override
    public double calculateBill() {
        return getUnits() * 8.0;
    }
}

class SharedRoom extends Room {
    private final int occupants;

    public SharedRoom(int units, int occupants) {
        super("SHARED", units);
        this.occupants = occupants;
    }

    public int getOccupants() {
        return occupants;
    }

    @Override
    public double calculateBill() {
        return (getUnits() * 6.0) / occupants;
    }
}

class ACRoom extends Room {
    public ACRoom(int units) {
        super("AC", units);
    }

    @Override
    public double calculateBill() {
        return getUnits() * 10.0 + 200.0;
    }
}

public class HostelElectricityBill {

    public static void processRooms(List<Room> rooms) {
        double grandTotal = 0.0;
        for (Room room : rooms) {
            double bill = room.calculateBill();
            grandTotal += bill;
            System.out.printf(Locale.US, "%s: %.2f%n", room.getType(), bill);
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
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int units = scanner.nextInt();
            if (type.equalsIgnoreCase("SHARED")) {
                int occupants = scanner.nextInt();
                rooms.add(new SharedRoom(units, occupants));
            } else if (type.equalsIgnoreCase("SINGLE")) {
                rooms.add(new SingleRoom(units));
            } else if (type.equalsIgnoreCase("AC")) {
                rooms.add(new ACRoom(units));
            } else {
                throw new IllegalArgumentException("Unknown room type: " + type);
            }
        }
        processRooms(rooms);
        scanner.close();
    }
}
