package Items;
import Entities.NPC;

/**
 * Represents a consumable item that can be used in combat.
 * These items provide various combat effects such as stuns, burns, or poisons
 * that can be applied to enemies over multiple turns.
 * This class extends the base Item class, adding combat-specific functionality.
 */
public class CombatConsumable extends Item {
    private String effectType;
    private int effectValue;
    private boolean stuns;
    private int duration;

    /**
     * Constructs a new combat consumable item.
     *
     * @param name The name of the consumable
     * @param effectType The type of effect (e.g., "Stuns", "Burns", "Poisons")
     * @param effectValue The potency or damage value of the effect
     * @param cost The gold cost of the item
     * @param stuns Whether the item has a stunning effect
     * @param duration The number of turns the effect lasts
     */
    public CombatConsumable(String name, String effectType, int effectValue, int cost, boolean stuns, int duration) {
        super(name, cost);
        this.effectType = effectType;
        this.effectValue = effectValue;
        this.stuns = stuns;
        this.duration = duration;
    }

    /**
     * Gets the type of effect this consumable applies.
     *
     * @return The effect type as a String (e.g., "Stuns", "Burns", "Poisons")
     */
    public String getEffectType() {
        return effectType;
    }

    /**
     * Gets the value or potency of the effect.
     * For damage effects, this represents the amount of damage dealt per turn.
     *
     * @return The effect value
     */
    public int getEffectValue() {
        return effectValue;
    }

    /**
     * Checks if this consumable has a stunning effect.
     *
     * @return true if the consumable stuns the target, false otherwise
     */
    public boolean getStuns() {
        return stuns;
    }

    /**
     * Gets the remaining duration of the effect in turns.
     *
     * @return The number of turns the effect will continue
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the remaining duration of the effect.
     *
     * @param duration The new duration value in turns
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Applies the initial effect of the consumable to an enemy.
     * This method handles the immediate effects when the item is first used.
     *
     * @param enemy The NPC target to apply the effect to
     */
    public void use(NPC enemy) {
        if (effectType.equals("Stuns")) {
            enemy.takeDamage(effectValue);
        } else if (effectType.equals("Burns")) {
        } else if (effectType.equals("Poisons")) {
        }
    }

    /**
     * Applies the ongoing effect of the consumable to an enemy.
     * This method is called each turn to handle damage-over-time effects
     * like burning or poison. It decrements the duration after applying the effect.
     *
     * @param enemy The NPC target to apply the ongoing effect to
     */
    public void applyEffect(NPC enemy) {
        if (duration > 0) {
            if (effectType.equals("Burns")) {
                int actualDamage = Math.min(effectValue, enemy.getHp());
                enemy.takeDamage(effectValue);
                System.out.println(enemy.getName() + " takes " + actualDamage + " burn damage! (Turns left: " + (duration - 1) + ")");
            }
            else if (effectType.equals("Poisons")) {
                int actualDamage = Math.min(effectValue, enemy.getHp());
                enemy.takeDamage(effectValue);
                System.out.println(enemy.getName() + " takes " + actualDamage + " poison damage! (Turns left: " + (duration - 1) + ")");
            }
            duration--;
        }
    }

    /**
     * Checks if the effect of this consumable is still active.
     *
     * @return true if the effect is still active (duration > 0), false otherwise
     */
    public boolean isActive() {
        return duration > 0;
    }
}