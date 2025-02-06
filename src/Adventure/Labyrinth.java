package Adventure;

import Entities.Hero;
import Entities.NPC;
import Entities.Vendor;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Labyrinth {
    private final Hero hero;
    private final Scanner scanner;
    private final Random random;
    private final Vendor vendor;
    private final ArrayList<String> locations = new ArrayList<>();
    private final ArrayList<NPC> normalEnemies = new ArrayList<>();
    private final ArrayList<NPC> miniBosses = new ArrayList<>();

    private int currentLocationIndex = 0;

    public Labyrinth(Hero hero) {
        this.hero = hero;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.vendor = new Vendor();
        initializeLocations();
        initializeEnemies();
    }

    private void initializeLocations() {
        locations.add("The Shire");
        locations.add("Bree");
        locations.add("Chetwood");
        locations.add("Weathertop");
        locations.add("Rivendell");
        locations.add("Goblin Caves");
        locations.add("Mines of Moria");
        locations.add("Rohan");
        locations.add("Fangorn Forest");
        locations.add("Helm’s Deep");
        locations.add("Isengard");
        locations.add("Minas Morgul");
        locations.add("Cirith Ungol");
        locations.add("Black Gate");
        locations.add("Plains of Gorgoroth");
        locations.add("Dark Tower of Barad-dûr");
    }

    private void initializeEnemies() {
        normalEnemies.add(new NPC("Orc Warrior", 35, 8, 15));
        normalEnemies.add(new NPC("Goblin Scout", 30, 5, 10));
        normalEnemies.add(new NPC("Uruk-hai", 45, 12, 20));
        normalEnemies.add(new NPC("Haradrim Soldier", 35, 10, 18));
        normalEnemies.add(new NPC("Warg Rider", 50, 14, 22));

        miniBosses.add(new NPC("Nazgûl", 80, 18, 40)); 
        miniBosses.add(new NPC("Balrog", 150, 30, 100)); 
        miniBosses.add(new NPC("Gríma Wormtongue", 70, 15, 50)); 
        miniBosses.add(new NPC("Saruman", 120, 28, 150)); 
        miniBosses.add(new NPC("Shelob", 160, 35, 200)); 
        miniBosses.add(new NPC("Witch-king of Angmar", 200, 40, 250)); 
    }

    public void start() {
        System.out.println(hero.getName() + " embarks on a journey through Middle-earth...");
        navigateLocation();
    }

    private void navigateLocation() {
        System.out.println("\nArriving at " + locations.get(currentLocationIndex));

        if (currentLocationIndex == 15) {
            finalBossBattle();
            return;
        }

        if (miniBosses.size() > 0 && (currentLocationIndex == 3 || currentLocationIndex == 6 || currentLocationIndex == 8 || currentLocationIndex == 10 || currentLocationIndex == 12 || currentLocationIndex == 14)) {
            encounterMiniBoss();
        } else {
            encounterEnemy();
        }

        choosePath();
    }

    private void encounterEnemy() {
        NPC enemy = normalEnemies.get(random.nextInt(normalEnemies.size()));
        System.out.println("An enemy appears! " + enemy.getName() + " is blocking your path!");
        battle(enemy);
    }

    private void encounterMiniBoss() {
        if (miniBosses.isEmpty()) {
            System.out.println("No mini-boss remains... This path is empty.");
            return;
        }

        NPC miniBoss = miniBosses.remove(0);
        System.out.println("A Mini-Boss appears: " + miniBoss.getName() + "!");
        battle(miniBoss);
    }

    private void battle(NPC enemy) {
        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("\nChoose your action:");
            System.out.println("[1] Normal Attack");
            System.out.println("[2] Special Attack (One-time per fight)");
            System.out.println("[3] Use a Combat Consumable");
            System.out.println("[4] Attempt to Flee");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    int damage = hero.getTotalAttackPower();
                    enemy.takeDamage(damage);
                }
                case 2 -> {
                    int damage = hero.getTotalAttackPower() * 2;
                    enemy.takeDamage(damage);
                    System.out.println(hero.getName() + " unleashes a Special Attack for " + damage + " damage!");
                }
                case 3 -> {
                    System.out.println("No consumables implemented yet!");
                }
                case 4 -> {
                    if (attemptFlee(enemy)) return;
                }
                default -> {
                    System.out.println("Invalid choice. Try again.");
                }
            }

            if (!enemy.isDefeated()) {
                int enemyDamage = enemy.getStrength();
                hero.setHp(hero.getHp() - enemyDamage);
                System.out.println(enemy.getName() + " strikes back for " + enemyDamage + " damage!");
            }
        }

        if (hero.getHp() > 0) {
            System.out.println(hero.getName() + " has defeated " + enemy.getName() + "!");
        } else {
            System.out.println(hero.getName() + " has fallen...");
            System.exit(0);
        }
    }

    private void choosePath() {
        System.out.println("\nChoose your next action:");
        System.out.println("[1] Take the safer route (More encounters, but easier)");
        System.out.println("[2] Take the dangerous shortcut (Fewer encounters, but stronger enemies)");
        System.out.print("Your choice: ");

        int choice = scanner.nextInt();
        currentLocationIndex += (choice == 1) ? 1 : 2;
        navigateLocation();
    }

    private boolean attemptFlee(NPC enemy) {
        System.out.println(hero.getName() + " tries to flee...");
        return random.nextBoolean();
    }

    private void finalBossBattle() {
        System.out.println("\nThe fires of Mount Doom crackle around you...");
        System.out.println("The Dark Tower of Barad-dûr looms above...");
        System.out.println("Sauron, the Dark Lord, stands before you!");

        NPC sauron = new NPC("Sauron", 300, 50, 1000);
        battle(sauron);

        if (hero.getHp() > 0) {
            System.out.println(hero.getName() + " has defeated Sauron!");
            System.out.println("Middle-earth is free once more!");
            System.out.println("You leave Mordor, forever remembered as a legend.");
            System.exit(0);
        } else {
            System.out.println(hero.getName() + " was slain... Middle-earth falls into darkness...");
            System.exit(0);
        }
    }
}
