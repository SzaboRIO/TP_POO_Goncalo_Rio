package Entities;

public class NPC {
    private String name;
    private int hp;
    private int strength;
    private int gold;

    public NPC(String name, int hp, int strength, int gold) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.gold = gold;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getGold() {
        return this.gold;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    public boolean isDefeated() {
        return this.hp <= 0;
    }

    public void applyStatusEffect(String effect, int turns) {
        System.out.println(name + " is affected by " + effect + " for " + turns + " turns.");
    }
}
