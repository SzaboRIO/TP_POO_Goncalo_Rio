package Entities;

import Items.MainWeapon;

/**
 * The Archer class represents an archer character in the game.
 * Archers are a specialized type of Hero that typically use bow weapons.
 * This class inherits all properties and behaviors from the Hero class.
 */
public class Archer extends Hero {

    /**
     * Constructs a new Archer with the specified attributes.
     * The archer is initialized with basic hero attributes and a weapon.
     *
     * @param name     The name of the archer
     * @param hp       The initial health points of the archer
     * @param strength The strength attribute of the archer, affecting attack power
     * @param gold     The initial amount of gold the archer possesses
     * @param weapon   The main weapon equipped by the archer, typically a bow
     */
    public Archer(String name, int hp, int strength, int gold, MainWeapon weapon) {
        super(name, hp, strength, gold, weapon);
    }
}