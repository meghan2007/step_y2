// Implement: Movie Ticket Counter
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Ticket {
    private static final double CONVENIENCE_FEE = 20.0;
    private final String seatType;
    private final int count;

    public Ticket(String seatType, int count) {
        this.seatType = seatType;
        this.count = count;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getCount() {
        return count;
    }

    public abstract double getBasePrice();

    public double calculateTotalAmount() {
        return count * (getBasePrice() + CONVENIENCE_FEE);
    }
}

class RegularTicket extends Ticket {
    public RegularTicket(int count) {
        super("REGULAR", count);
    }

    @Override
    public double getBasePrice() {
        return 150.0;
    }
}

class PremiumTicket extends Ticket {
    public PremiumTicket(int count) {
        super("PREMIUM", count);
    }

    @Override
    public double getBasePrice() {
        return 250.0;
    }
}

class ReclinerTicket extends Ticket {
    public ReclinerTicket(int count) {
        super("RECLINER", count);
    }

    @Override
    public double getBasePrice() {
        return 400.0;
    }
}

public class MovieTicketCounter {

    public static Ticket createTicket(String seatType, int count) {
        switch (seatType.trim().toUpperCase()) {
            case "REGULAR":
                return new RegularTicket(count);
            case "PREMIUM":
                return new PremiumTicket(count);
            case "RECLINER":
                return new ReclinerTicket(count);
            default:
                throw new IllegalArgumentException("Unknown seat type: " + seatType);
        }
    }

    public static void processBookings(List<Ticket> bookings) {
        double grandTotal = 0.0;
        for (Ticket ticket : bookings) {
            double amount = ticket.calculateTotalAmount();
            grandTotal += amount;
            System.out.printf(Locale.US, "%s: %.2f%n", ticket.getSeatType(), amount);
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
        List<Ticket> bookings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String seat = scanner.next();
            int count = scanner.nextInt();
            bookings.add(createTicket(seat, count));
        }
        processBookings(bookings);
        scanner.close();
    }
}
