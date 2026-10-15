public class Character {
    private int health;
    private final int maxHealth;

    public Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public void heal(int amount) {
        this.health = Math.min(this.maxHealth, this.health + amount);
    }

    public int getHealth() {
        return this.health;
    }
}
