package Entities;

/**
 * Represents a Non-Player Character (NPC) in the game.
 * NPCs are characters controlled by the game rather than the player,
 * and include enemies.
 */
public class NPC {
    private String name;
    private int hp;
    private int strength;
    private int gold;
    private String category;
    private boolean missNextAttack = false;

    /**
     * Constructs a new NPC with the specified attributes.
     *
     * @param name The NPC's name
     * @param hp The NPC's health points
     * @param strength The NPC's strength attribute
     * @param gold The amount of gold the NPC possesses/rewards
     * @param categoria The category/type of the NPC
     */
    public NPC(String name, int hp, int strength, int gold, String categoria) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.gold = gold;
        this.category = category;
    }

    /**
     * Sets whether the NPC should miss their next attack.
     * This can be used for status effects or combat mechanics.
     *
     * @param missNextAttack true if the NPC should miss their next attack, false otherwise
     */
    public void setMissNextAttack(boolean missNextAttack) {
        this.missNextAttack = missNextAttack;
    }

    /**
     * @return The NPC's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The NPC's current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * @param newHp The new health points value
     */
    public void setHp(int newHp) {
        this.hp = newHp;
    }

    /**
     * @return The NPC's strength value
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return The NPC's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the amount of gold the NPC possesses.
     * This represents the reward for defeating the NPC.
     *
     * @return The amount of gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * Applies damage to the NPC, reducing their current HP.
     * Health points cannot go below zero.
     *
     * @param damage The amount of damage to apply
     */
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    /**
     * Checks if the NPC is defeated (has zero HP).
     *
     * @return true if the NPC is defeated, false otherwise
     */
    public boolean isDefeated() {
        return this.hp <= 0;
    }
}