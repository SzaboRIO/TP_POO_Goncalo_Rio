package Entities;

import Items.MainWeapon;

public abstract class Hero {
    protected String name;
    protected int hp;
    protected int strength;
    protected int gold;
    protected MainWeapon mainWeapon;

    public Hero(String name, int hp, int strength, int gold, MainWeapon weapon) {
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.gold = gold;
        this.mainWeapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getTotalAttackPower() {
        return strength + (mainWeapon != null ? mainWeapon.getAttackPower() : 0);
    }

    public void equipWeapon(MainWeapon weapon) {
        this.mainWeapon = weapon;
    }
}
