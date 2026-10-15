public class PasswordChecker {
    private String password;

    public PasswordChecker(String password) {
        this.password = password;
    }

    public String getStrength() {
        int len = password.length();
        if (len < 6) return "Weak";
        if (len <= 9) return "Medium";
        return "Strong";
    }
}
