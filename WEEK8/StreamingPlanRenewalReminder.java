import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SubscriptionPlan {
    private final String subscriberName;
    private final LocalDate startDate;

    public SubscriptionPlan(String subscriberName, LocalDate startDate) {
        this.subscriberName = subscriberName;
        this.startDate = startDate;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public abstract int getValidityDays();

    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(getValidityDays());
    }
}

class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public int getValidityDays() {
        return 30;
    }
}

class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public int getValidityDays() {
        return 90;
    }
}

class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public int getValidityDays() {
        return 365;
    }
}

public class StreamingPlanRenewalReminder {

    public static SubscriptionPlan createPlan(String type, String name, LocalDate startDate) {
        switch (type.trim().toUpperCase()) {
            case "BASIC":
                return new BasicPlan(name, startDate);
            case "STANDARD":
                return new StandardPlan(name, startDate);
            case "PREMIUM":
                return new PremiumPlan(name, startDate);
            default:
                throw new IllegalArgumentException("Unknown plan type: " + type);
        }
    }

    public static void processSubscriptions(List<SubscriptionPlan> subscriptions) {
        for (SubscriptionPlan plan : subscriptions) {
            System.out.println(plan.getSubscriberName() + ": " + plan.calculateRenewalDate());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        List<SubscriptionPlan> subscriptions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String planType = scanner.next();
            String name = scanner.next();
            String dateStr = scanner.next();
            LocalDate startDate = LocalDate.parse(dateStr.trim());
            subscriptions.add(createPlan(planType, name, startDate));
        }
        processSubscriptions(subscriptions);
        scanner.close();
    }
}
