package Items;

import Entities.Archer;
import Entities.Hero;
import Entities.Mage;
import Entities.Warrior;
import Entities.NPC;
import java.util.Objects;

/**
 * Represents a main weapon item that can be used by heroes.
 * This class defines the properties and functionalities of a main weapon,
 * including its type, attack power, special effects, and usability by different hero types.
 */
public class MainWeapon extends Item{


    private final String type;
    private final int attackPower;
    private final String specialEffect;
    private final String specialEffectType;
    private final double effectChance;
    private final String effectTarget;
    private final int effectValue;

    /**
     * Constructs a new MainWeapon object.
     *
     * @param name              The name of the weapon.
     * @param type              The type of the weapon. (e.g., "sword", "bow", "staff")
     * @param attackPower       The attack power of the weapon.
     * @param specialEffect     The special effect of the weapon. (e.g., "Burns enemies", "Strong vs. Legendary Enemies")
     * @param cost              The cost of the weapon.
     * @param specialEffectType The type of special effect. (e.g., "onHit", "passive")
     * @param effectChance      The chance of the special effect activating.
     * @param effectTarget      The target of the special effect. ("self", "enemy", or null)
     * @param effectValue       The value of the special effect. (e.g., damage, healing)
     */
    public MainWeapon(String name, String type, int attackPower, String specialEffect, int cost, String specialEffectType, double effectChance, String effectTarget, int effectValue) {
        super(name, cost);
        this.type = type;
        this.attackPower = attackPower;
        this.specialEffect = specialEffect;
        this.specialEffectType = specialEffectType;
        this.effectChance = effectChance;
        this.effectTarget = effectTarget;
        this.effectValue = effectValue;
    }
    /**
     * Constructs starting MainWeapons with default values.
     *
     * @param name          The name of the weapon.
     * @param type          The type of the weapon.
     * @param attackPower   The attack power of the weapon.
     * @param specialEffect The special effect of the weapon.
     * @param cost          The cost of the weapon.
     */
    public MainWeapon(String name, String type, int attackPower, String specialEffect, int cost) {
        this(name, type, attackPower, specialEffect, cost, null, 0.0, null, 0);
    }

    /**
     * @return The type of the weapon.
     */
    public String getType() {
        return type;
    }

    /**
     * @return The attack power of the weapon.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * @return The special effect of the weapon.
     */
    public String getSpecialEffect() {
        return specialEffect;
    }

    /**
     * @return The type of the special effect.
     */
    public String getSpecialEffectType(){
        return specialEffectType;
    }

    /**
     * @return The chance of the special effect activating.
     */
    public double getEffectChance(){
        return effectChance;
    }

    /**
     * @return The target of the special effect.
     */
    public String getEffectTarget(){
        return  effectTarget;
    }

    /**
     * @return The value of the special effect.
     */
    public int getEffectValue(){
        return effectValue;
    }

    /**
     * @return The name of the weapon.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Checks if a hero can use this weapon.
     *
     * @param hero The hero to check.
     * @return True if the hero can use the weapon, false otherwise.
     */
    public boolean canUse(Hero hero) {
        if (this.type.equals("sword") && hero instanceof Warrior) {
            return true;
        } else if (this.type.equals("bow") && hero instanceof Archer) {
            return true;
        } else if (this.type.equals("staff") && hero instanceof Mage) {
            return true;
        }
        return false;
    }

    /**
     * Checks if two MainWeapon objects are equal based on their name.
     *
     * @param o The object to compare with this MainWeapon.
     * @return True if the specified object is equal to this MainWeapon, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainWeapon that = (MainWeapon) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Applies the weapon's special effect to the specified enemy.
     *
     * @param enemy The enemy to apply the effect to.
     */
    public void applyWeaponEffect(NPC enemy) {
        if ("onHit".equals(this.specialEffectType) && this.specialEffect!= null) {
            if (Math.random() < this.effectChance) {
                switch (this.specialEffect) {
                    case "🔥 Burns enemies":
                        if (!enemy.isDefeated()) {
                            enemy.takeDamage(this.effectValue);
                            System.out.println(enemy.getName() + " is burned by " + this.getName() + " for an extra " + this.getEffectValue() + " damage!");
                        }
                        break;
                    case "⚔️ Strong vs. Legendary Enemies":
                        if (!enemy.isDefeated()) {
                            if ("legendaryEnemy".equals(enemy.getCategory())) {
                                enemy.takeDamage(this.effectValue);
                                System.out.println(this.getName() + " deals extra " + this.getEffectValue() + " damage to " + enemy.getName() + "!");
                            }
                        }
                        break;
                    case "💥 Strong vs. Orc Kind":
                        if (!enemy.isDefeated()) {
                            if ("orcKindEnemy".equals(enemy.getCategory())) {
                                enemy.takeDamage(this.effectValue);
                                System.out.println(this.getName() + " deals extra " + this.getEffectValue() + "  damage to " + enemy.getName() + "!");
                            }
                        }
                        break;
                    case "\uD83C\uDFAF Penetrates armor":
                        if (!enemy.isDefeated()) {
                            enemy.takeDamage(this.effectValue);
                            System.out.println(this.getName() + " shoots a precise arrow that penetrates armor and deals extra " + this.getEffectValue() + " damage to " + enemy.getName() + "!");
                        }
                        break;
                    case "🌀 Confuses enemies":
                        if(!enemy.isDefeated()){
                            enemy.setMissNextAttack(true);
                            System.out.println(enemy.getName() + " got confused!");
                        }
                        break;
                    default:
                        System.out.println("Unknown weapon effect: " + this.specialEffect);
                }
            }
        }
    }
}