package Entities;

import Items.CombatConsumable;
import Items.Item;
import Items.MainWeapon;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a hero character in the game.
 * This class encapsulates common attributes and behaviors that all heroes share,
 * such as health, strength, inventory management, and combat capabilities.
 */
public abstract class Hero {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int strength;
    protected int gold;
    protected MainWeapon mainWeapon;
    protected ArrayList<Item> inventory;
    protected ArrayList<Item> boughtItems;

    /**
     * Constructs a new Hero with the specified attributes.
     *
     * @param name The hero's name
     * @param hp The hero's initial and maximum health points
     * @param strength The hero's base strength attribute
     * @param gold The hero's initial gold amount
     * @param weapon The hero's initial main weapon
     */
    public Hero(String name, int hp, int strength, int gold, MainWeapon weapon) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.strength = strength;
        this.gold = gold;
        this.mainWeapon = weapon;
        this.inventory = new ArrayList<>();
        this.boughtItems = new ArrayList<>();
    }

    /**
     * Gets the hero's name.
     *
     * @return The hero's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the hero's current health points.
     *
     * @return The hero's current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets the hero's main weapon.
     *
     * @return The hero's currently equipped main weapon
     */
    public MainWeapon getMainWeapon() {
        return mainWeapon;
    }

    /**
     * Sets the hero's main weapon.
     *
     * @param weapon The main weapon to equip
     */
    public void setMainWeapon(MainWeapon weapon) {
        this.mainWeapon = weapon;
    }

    /**
     * Gets the hero's maximum health points.
     *
     * @return The hero's maximum HP
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Sets the hero's maximum health points.
     *
     * @param maxHp The new maximum HP value
     */
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    /**
     * Sets the hero's current health points.
     *
     * @param hp The new HP value
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Sets the hero's strength attribute.
     *
     * @param strength The new strength value
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Checks if the hero has any consumable items in their inventory.
     *
     * @return true if the hero has at least one consumable item, false otherwise
     */
    public boolean hasConsumables() {
        return !inventory.isEmpty();
    }

    /**
     * Checks if the hero has any combat consumable items in their inventory.
     *
     * @return true if the hero has at least one combat consumable item, false otherwise
     */
    public boolean hasCombatConsumables() {
        for (Item item : inventory) {
            if (item instanceof CombatConsumable) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the hero's strength attribute.
     *
     * @return The hero's strength value
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Gets the hero's current gold amount.
     *
     * @return The hero's gold
     */
    public int getMoney() {
        return gold;
    }

    /**
     * Sets the hero's gold amount.
     *
     * @param gold The new gold amount
     */
    public void setMoney(int gold) {
        this.gold = gold;
    }

    /**
     * Gets the hero's inventory.
     *
     * @return The ArrayList containing all items in the hero's inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Calculates the hero's total attack power.
     * This includes base strength plus the attack power of the equipped weapon (if any).
     *
     * @return The hero's total attack power
     */
    public int getTotalAttackPower() {
        return strength + (mainWeapon != null ? mainWeapon.getAttackPower() : 0);
    }

    /**
     * Equips a weapon and applies any passive effects it may have.
     * Passive effects can enhance the hero's attributes like strength or maximum HP.
     *
     * @param weapon The weapon to equip
     */
    public void equipWeapon(MainWeapon weapon) {
        this.mainWeapon = weapon;
        if ("passive".equals(weapon.getSpecialEffectType())) {
            switch (weapon.getSpecialEffect()) {
                case "\uD83D\uDCAA\uD83D\uDC96 Enhances Strength and MaxHp":
                    this.strength += weapon.getEffectValue();
                    this.maxHp += weapon.getEffectValue();
                    break;
                case "\uD83D\uDCAA Enhances Strength":
                    this.strength += weapon.getEffectValue();
                    break;
            }
        }
    }

    /**
     * Gets the currently equipped weapon.
     *
     * @return The hero's currently equipped main weapon
     */
    public MainWeapon getEquippedWeapon() {
        return this.mainWeapon;
    }

    /**
     * Unequips a weapon and removes any passive effects it provided.
     * If removing an effect would reduce maximum HP below current HP,
     * current HP is adjusted accordingly.
     *
     * @param weapon The weapon to unequip
     */
    public void unequipWeapon(MainWeapon weapon) {
        if ("passive".equals(weapon.getSpecialEffectType())) {
            switch (weapon.getSpecialEffect()) {
                case "\uD83D\uDCAA\uD83D\uDC96 Enhances Strength and MaxHp":
                    this.strength -= weapon.getEffectValue();
                    this.maxHp -= weapon.getEffectValue();
                    if (this.hp > this.maxHp) {
                        this.hp = this.maxHp;
                    }
                    break;
                case "\uD83D\uDCAA Enhances Strength":
                    this.strength -= weapon.getEffectValue();
                    break;
            }
        }
    }

    /**
     * Adds an item to the hero's inventory.
     *
     * @param item The item to add to the inventory
     */
    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    /**
     * Gets the list of items the hero has purchased.
     *
     * @return The list of purchased items
     */
    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    /**
     * Applies damage to the hero, reducing their current HP.
     * Health points cannot go below zero.
     *
     * @param damage The amount of damage to apply
     */
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
}