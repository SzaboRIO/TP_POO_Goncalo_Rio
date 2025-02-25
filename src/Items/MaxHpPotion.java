package Items;
import Entities.Hero;

/**
 * Represents a Max HP Potion consumable item.
 * This potion increases the hero's maximum health.
 */
public class MaxHpPotion extends Consumable{
    /**
     * Constructs a new MaxHpPotion object.
     *
     * @param name       The name of the potion.
     * @param maxHpBoost The amount of maximum HP to boost.
     * @param cost       The cost of the potion.
     */
    public MaxHpPotion(String name, int maxHpBoost, int cost){
        super(name, "hpBoost", maxHpBoost, cost);
    }

    /**
     * Uses the potion on the specified hero, increasing their maximum HP.
     *
     * @param hero The hero consuming the potion.
     */
    @Override
    public void use(Hero hero){
        hero.setMaxHp(hero.getMaxHp()+getEffectValue());
        System.out.println(hero.getName() + " gained " + getEffectValue() + " max HP!");
    }
}