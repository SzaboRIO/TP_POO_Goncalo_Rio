package Entities;

import Items.CombatConsumable;
import Items.MainWeapon;
import java.util.ArrayList;
import java.util.Random;

public class Vendor {
    private final ArrayList<Object> inventory = new ArrayList<>();
    private final Random random = new Random();

    public Vendor() {
        generateInventory();
    }

    private void generateInventory() {
        inventory.add(new CombatConsumable("Lembas Bread", "Restores HP", 20));
        inventory.add(new CombatConsumable("Athelas", "Heals wounds", 30));
        inventory.add(new MainWeapon("Andúril", 50, 100));
    }

    public void showInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i).toString());
        }
    }
}
