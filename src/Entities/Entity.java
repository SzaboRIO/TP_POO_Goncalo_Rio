package Entities;

public abstract class Entity {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int strength;

    public Entity(String name, int maxHp, int strength) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp; // Current health starts equal to max health
        this.strength = strength;
    }

    public void showDetails() {
        System.out.println("📜 Character Info 📜");
        System.out.println("🏷️ Name: " + name);
        System.out.println("❤️ Health: " + hp + "/" + maxHp);
        System.out.println("💪 Strength: " + strength);
        System.out.println("----------------------");
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
