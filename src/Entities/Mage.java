package Entities;

import Items.MainWeapon;

/**
 * Represents a Mage character in the game, a specific type of Hero.
 * Mages are specialized hero characters with unique attributes and abilities.
 * This class extends the abstract Hero class, inheriting basic hero functionalities.
 */
public class Mage extends Hero {

    /**
     * Constructs a new Mage character with the specified attributes.
     *
     * @param name The mage's name
     * @param hp The mage's initial and maximum health points
     * @param strength The mage's base strength attribute
     * @param gold The mage's initial gold amount
     * @param weapon The mage's initial main weapon
     */
    public Mage(String name, int hp, int strength, int gold, MainWeapon weapon) {
        super(name, hp, strength, gold, weapon);
    }
}