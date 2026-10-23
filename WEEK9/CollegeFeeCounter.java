// Implement: College Fee Counter
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

interface BusUser {
    double TRANSPORT_FEE = 12000.0;

    default double getTransportFee() {
        return TRANSPORT_FEE;
    }
}

abstract class Student {
    protected static final double NORMAL_TUITION = 40000.0;
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateTuition();

    public double calculateTotalFee() {
        double fee = calculateTuition();
        if (this instanceof BusUser) {
            fee += ((BusUser) this).getTransportFee();
        }
        return fee;
    }
}

class DayScholarStudent extends Student implements BusUser {
    public DayScholarStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return NORMAL_TUITION;
    }
}

class HostellerStudent extends Student {
    private static final double HOSTEL_FEE = 60000.0;

    public HostellerStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return NORMAL_TUITION + HOSTEL_FEE;
    }
}

class ScholarshipStudent extends Student implements BusUser {
    public ScholarshipStudent(String name) {
        super(name);
    }

    @Override
    public double calculateTuition() {
        return NORMAL_TUITION / 2.0;
    }
}

public class CollegeFeeCounter {

    public static Student createStudent(String type, String name) {
        switch (type.trim().toUpperCase()) {
            case "DAY_SCHOLAR":
                return new DayScholarStudent(name);
            case "HOSTELLER":
                return new HostellerStudent(name);
            case "SCHOLAR":
                return new ScholarshipStudent(name);
            default:
                throw new IllegalArgumentException("Unknown student type: " + type);
        }
    }

    public static void processStudents(List<Student> students) {
        double grandTotal = 0.0;
        for (Student student : students) {
            double totalFee = student.calculateTotalFee();
            grandTotal += totalFee;
            System.out.printf(Locale.US, "%s: %.2f%n", student.getName(), totalFee);
        }
        System.out.printf(Locale.US, "Total Collected: %.2f%n", grandTotal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            students.add(createStudent(type, name));
        }
        processStudents(students);
        scanner.close();
    }
}
