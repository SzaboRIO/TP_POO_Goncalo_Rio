package Items;
import Entities.Hero;

/**
 * Represents a health potion that can be used to heal a hero.
 * Extends the Consumable class.
 */
public class HealthPotion extends Consumable {
    private int stock;

    /**
     * Constructs a HealthPotion object with the specified name, heal amount, and cost.
     *
     * @param name       The name of the health potion.
     * @param healAmount The amount of health restored when used.
     * @param cost       The cost of the health potion.
     */
    public HealthPotion(String name, int healAmount, int cost) {
        super(name, "heal", healAmount, cost);
        this.stock = 0;
    }

    /**
     * Gets the current stock of the health potion.
     *
     * @return The number of potions in stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the health potion.
     *
     * @param stock The new stock amount.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Uses the health potion on the specified hero.
     * Restores health up to the hero's maximum HP.
     *
     * @param hero The hero who will use the potion.
     */
    @Override
    public void use(Hero hero) {
        int newHp = hero.getHp() + getEffectValue();
        if (newHp > hero.getMaxHp()) {
            newHp = hero.getMaxHp();
        }
        hero.setHp(newHp);
        System.out.println(hero.getName() + " healed for " + getEffectValue() + " HP.");
    }

    /**
     * Returns a string representation of the health potion.
     *
     * @return A formatted string displaying the potion's name, effect, stock, and cost.
     */
    @Override
    public String toString() {
        return getName() + " (heal effect: " + getEffectValue() + ") x" + stock + " * " + getCost() + " 💰";
    }
}
