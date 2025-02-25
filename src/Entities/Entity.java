package Entities;

/**
 * An abstract base class that represents any living entity in the game world.
 * This class defines common attributes and behaviors shared by all entities
 * such as heroes, enemies, and NPCs.
 */
public abstract class Entity {
    /** The name of the entity */
    protected String name;

    /** The maximum health points the entity can have */
    protected int maxHp;

    /** The current health points of the entity */
    protected int hp;

    /** The strength attribute of the entity, affecting attack power */
    protected int strength;

    /**
     * Constructs a new Entity with the specified attributes.
     * The entity's current health is initialized to its maximum health.
     *
     * @param name     The name of the entity
     * @param maxHp    The maximum health points the entity can have
     * @param strength The entity's strength attribute
     */
    public Entity(String name, int maxHp, int strength) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.strength = strength;
    }

    /**
     * @return The entity's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The entity's current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the current health points of the entity.
     * Note: This method does not enforce the maximum health limit.
     *
     * @param hp The new health points value
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * @return The entity's strength value
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Sets the strength attribute of the entity.
     *
     * @param strength The new strength value
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
}