package Items;

import Entities.Hero;

/**
 * Represents a Strength Potion consumable item.
 * This potion increases the hero's strength.
 */
public class StrengthPotion extends Consumable{
    /**
     * Constructs a new StrengthPotion object.
     *
     * @param name          The name of the potion.
     * @param strengthBoost The amount of strength to boost.
     * @param cost          The cost of the potion.
     */
    public StrengthPotion(String name, int strengthBoost, int cost){
        super(name, "strBoost", strengthBoost, cost);
    }

    /**
     * Uses the potion on the specified hero, increasing their strength.
     *
     * @param hero The hero consuming the potion.
     */
    @Override
    public void use(Hero hero){
        hero.setStrength(hero.getStrength() + getEffectValue());
        System.out.println(hero.getName() + " gained " + getEffectValue() + " strength!");
    }
}