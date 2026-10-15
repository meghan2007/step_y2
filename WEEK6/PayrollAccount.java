public class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: basic salary cannot be negative. Starting at 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus must be positive.");
        } else {
            this.bonus += amount;
        }
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Invalid tax percentage.");
        } else {
            this.basicSalary -= this.basicSalary * (percent / 100.0);
        }
    }

    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
}
