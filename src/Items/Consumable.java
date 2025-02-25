package Items;
import Entities.Hero;

/**
 * An abstract class representing consumable items in the game.
 * Consumables are items that can be used by heroes to produce various effects,
 * such as healing, stat boosts, or other temporary or permanent changes.
 * This class extends the base Item class and defines common properties and
 * behaviors for all consumable items.
 */
public abstract class Consumable extends Item {
    protected final String effectType;
    protected final int effectValue;

    /**
     * Constructs a new consumable item with the specified attributes.
     *
     * @param name The name of the consumable
     * @param effectType The type of effect this consumable produces (e.g., "Healing", "Strength Boost")
     * @param effectValue The magnitude or potency of the effect
     * @param cost The gold cost of the item
     */
    public Consumable(String name, String effectType, int effectValue, int cost) {
        super(name, cost);
        this.effectType = effectType;
        this.effectValue = effectValue;
    }

    /**
     * Gets the type of effect this consumable produces.
     *
     * @return The effect type as a String
     */
    public String getEffectType() {
        return effectType;
    }

    /**
     * Gets the magnitude or potency of the effect.
     *
     * @return The effect value as an integer
     */
    public int getEffectValue() {
        return effectValue;
    }

    /**
     * Applies the consumable's effect to a hero.
     * This method must be implemented by concrete subclasses to define
     * the specific behavior of each type of consumable.
     *
     * @param hero The hero to which the consumable's effect will be applied
     */
    public abstract void use(Hero hero);
}