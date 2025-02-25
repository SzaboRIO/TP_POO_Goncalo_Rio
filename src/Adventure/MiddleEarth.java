package Adventure;

import Entities.Hero;
import Entities.NPC;
import Entities.Vendor;
import Items.CombatConsumable;
import Items.Consumable;
import Items.Item;
import Items.MainWeapon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Thread.sleep;

/**
 * Represents the world of Middle-earth where the hero embarks on a journey.
 * Handles game progression, locations, enemies, battles, and interactions.
 */
public class MiddleEarth {
    private final Hero hero;
    private final Scanner scanner;
    private final Random random;
    private final Vendor vendor;
    private final ArrayList<String> locations = new ArrayList<>();
    private final ArrayList<NPC> normalEnemies = new ArrayList<>();
    private final ArrayList<NPC> miniBosses = new ArrayList<>();
    private int currentLocationIndex = 0;
    private boolean gollumHasAppeared = false;
    private NPC currentEnemy = null;

    /**
     * Constructs a MiddleEarth instance with the given hero.
     *
     * @param hero The hero character controlled by the player.
     */
    public MiddleEarth(Hero hero) {
        this.hero = hero;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.vendor = new Vendor();
        initializeLocations();
        initializeEnemies();
    }

    /**
     * Initializes the locations the hero can visit during the journey.
     */
    private void initializeLocations() {
        locations.add("\uD83C\uDF3F The Shire");
        locations.add("\uD83C\uDFE1 Bree");
        locations.add("\uD83C\uDF32 Chetwood");
        locations.add("\uD83C\uDFD4\uFE0F Weathertop");
        locations.add("🌲 Trollshaws");
        locations.add("\uD83C\uDFF0 Rivendell");
        locations.add("\uD83C\uDFDA\uFE0F Goblin Caves");
        locations.add("❄\uFE0F Misty Mountains ");
        locations.add("⚒\uFE0F Mines of Moria");
        locations.add("\uD83C\uDFD4\uFE0F Dimrill Dale ");
        locations.add("🌳 Lothlórien");
        locations.add("🌊 Anduin River");
        locations.add("\uD83C\uDFC7 Rohan");
        locations.add("🏞️ Westfold");
        locations.add("\uD83C\uDFF0 Helm’s Deep");
        locations.add("\uD83C\uDFF0 Isengard");
        locations.add("\uD83C\uDF32 Fangorn Forest");
        locations.add("⚔️ Gondor");
        locations.add("\uD83D\uDD77\uFE0F Cirith Ungol");
        locations.add("\uD83C\uDFDB\uFE0F Osgiliath");
        locations.add("\uD83C\uDFF0 Minas Morgul");
        locations.add("\uD83D\uDEAA Black Gate");
        locations.add("\uD83C\uDF0B Mordor");
        locations.add("\uD83D\uDD25 Plains of Gorgoroth");
        locations.add("⚫ Dark Tower of Barad-dûr");
    }

    /**
     * Initializes the enemies the hero may encounter.
     */
    private void initializeEnemies() {
        normalEnemies.add(new NPC("⚔\uFE0F Orc Warrior", 112, 13, 15, "orcKindEnemy"));
        normalEnemies.add(new NPC("\uD83C\uDFF9 Goblin Scout", 100, 10, 10, "orcKindEnemy"));
        normalEnemies.add(new NPC("⚔\uFE0F Uruk-hai", 140, 15, 20, "orcKindEnemy"));
        normalEnemies.add(new NPC("\uD83C\uDFF9 Haradrim Soldier", 110, 12, 18, "humanKindEnemy"));
        normalEnemies.add(new NPC("\uD83D\uDDE1\uFE0F Easterling", 120, 11, 22, "humanKindEnemy"));
        normalEnemies.add(new NPC("⚓ Corsair of Umbar", 110, 12, 22, "humanKindEnemy"));
        normalEnemies.add(new NPC("\uD83D\uDDE1\uFE0F\uD83D\uDC3A Warg Rider", 140, 16, 22, "orcKindEnemy"));

        miniBosses.add(new NPC("\uD83D\uDC41\uFE0F Nazgûl", 180, 20, 40, "legendaryEnemy"));
        miniBosses.add(new NPC("\uD83D\uDD25 Balrog", 250, 23, 100, "legendaryEnemy"));
        miniBosses.add(new NPC("\uD83D\uDC0D Gríma Wormtongue", 170, 18, 50, "legendaryEnemy"));
        miniBosses.add(new NPC("\uD83E\uDDD9\u200D♂\uFE0F Saruman", 210, 26, 150, "legendaryEnemy"));
        miniBosses.add(new NPC("\uD83D\uDD77\uFE0F Shelob", 280, 25, 200, "legendaryEnemy"));
        miniBosses.add(new NPC("\uD83D\uDC51 Witch-king of Angmar", 350, 28, 250, "legendaryEnemy"));
    }

    /**
     * Starts the hero's journey through Middle-earth.
     *
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    public void start() throws FileNotFoundException, InterruptedException {
        System.out.println("\n" + hero.getName() + " embarks on a journey through Middle-earth...");
        System.out.println("You must travel to Mordor to defeat Sauron and restore Middle-earth to its former glory free of darkness!");
        System.out.println("Your journey begins now...");

        System.out.println();
        Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/vendor.txt");
        Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/vendor.wav");
        System.out.println("\nGollum appears out of nowhere!");
        vendor.sellItem(hero); // Initial vendor visit
        gollumHasAppeared = true;

        while (hero.getHp() > 0 && currentLocationIndex < locations.size()) {
            navigateLocation();

            if (hero.getHp() <= 0) {
                System.out.println("\n" + hero.getName() + " has fallen in battle...");
                System.out.println("Game Over!");
                break;
            }
            if (currentLocationIndex >= locations.size()) {
                System.out.println("\nCongratulations!  You have completed your journey!");
                break;
            }
        }
    }

    /**
     * Navigates to the next location and handles events at that location.
     *
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    private void navigateLocation() throws FileNotFoundException, InterruptedException {
        String currentLocation = locations.get(currentLocationIndex);
        System.out.println("\n\uD83D\uDED1 **Arriving at " + locations.get(currentLocationIndex));

        // Gollum appears every 4th location (4, 8, 12, etc.)
        if (!gollumHasAppeared || (currentLocationIndex + 1) % 4 == 0) {
            System.out.println();
            Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/vendor.txt");
            Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/vendor.wav");
            System.out.println("\nGollum appears out of nowhere!");
            vendor.sellItem(hero);
        }

        if (currentLocationIndex == 24) {
            finalBossBattle();
            return;
        }

        if (miniBosses.size() > 0 && (currentLocationIndex == 3 || currentLocationIndex == 8 || currentLocationIndex == 12 || currentLocationIndex == 15 || currentLocationIndex == 18 || currentLocationIndex == 21)) {
            encounterMiniBoss();
        } else {
            //Regular encounter
            switch (currentLocationIndex) {
                case 0: // Shire
                    NPC randomEnemy0 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy0.getName(), randomEnemy0.getHp(), randomEnemy0.getStrength(), randomEnemy0.getGold(), randomEnemy0.getCategory())); // Create new enemy
                    break;
                case 1: // Bree
                    NPC randomEnemy1 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy1.getName(), randomEnemy1.getHp(), randomEnemy1.getStrength(), randomEnemy1.getGold(), randomEnemy1.getCategory())); // Create new enemy
                    break;
                case 2: // Chetwood
                    NPC randomEnemy2 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy2.getName(), randomEnemy2.getHp(), randomEnemy2.getStrength(), randomEnemy2.getGold(), randomEnemy2.getCategory())); // Create new enemy
                    break;
                case 4: // Trollshaws
                    NPC randomEnemy4 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy4.getName(), randomEnemy4.getHp(), randomEnemy4.getStrength(), randomEnemy4.getGold(), randomEnemy4.getCategory())); // Create new enemy
                    break;
                case 5: // Rivendell
                    NPC randomEnemy5 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy5.getName(), randomEnemy5.getHp(), randomEnemy5.getStrength(), randomEnemy5.getGold(), randomEnemy5.getCategory())); // Create new enemy
                    break;
                case 6: // Goblin Caves
                    NPC randomEnemy6 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy6.getName(), randomEnemy6.getHp(), randomEnemy6.getStrength(), randomEnemy6.getGold(), randomEnemy6.getCategory())); // Create new enemy
                    break;
                case 7: // Misty Mountains
                    NPC randomEnemy7 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy7.getName(), randomEnemy7.getHp(), randomEnemy7.getStrength(), randomEnemy7.getGold(), randomEnemy7.getCategory())); // Create new enemy
                    break;
                case 9: // Dimrill Dale
                    NPC randomEnemy9 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy9.getName(), randomEnemy9.getHp(), randomEnemy9.getStrength(), randomEnemy9.getGold(), randomEnemy9.getCategory())); // Create new enemy
                    break;
                case 10: // Lothlorien
                    NPC randomEnemy10 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy10.getName(), randomEnemy10.getHp(), randomEnemy10.getStrength(), randomEnemy10.getGold(), randomEnemy10.getCategory())); // Create new enemy
                    break;
                case 11: // Anduin River
                    NPC randomEnemy11 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy11.getName(), randomEnemy11.getHp(), randomEnemy11.getStrength(), randomEnemy11.getGold(), randomEnemy11.getCategory())); // Create new enemy
                    break;
                case 13: // Westfold
                    NPC randomEnemy13 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy13.getName(), randomEnemy13.getHp(), randomEnemy13.getStrength(), randomEnemy13.getGold(), randomEnemy13.getCategory())); // Create new enemy
                    break;
                case 14: // Helm's Deep
                    NPC randomEnemy14 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy14.getName(), randomEnemy14.getHp(), randomEnemy14.getStrength(), randomEnemy14.getGold(), randomEnemy14.getCategory())); // Create new enemy
                    break;
                case 16: // Fangorn Forest
                    NPC randomEnemy16 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy16.getName(), randomEnemy16.getHp(), randomEnemy16.getStrength(), randomEnemy16.getGold(), randomEnemy16.getCategory())); // Create new enemy
                    break;
                case 17: // Gondor
                    NPC randomEnemy17 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy17.getName(), randomEnemy17.getHp(), randomEnemy17.getStrength(), randomEnemy17.getGold(), randomEnemy17.getCategory())); // Create new enemy
                    break;
                case 19: // Osgiliath
                    NPC randomEnemy19 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy19.getName(), randomEnemy19.getHp(), randomEnemy19.getStrength(), randomEnemy19.getGold(), randomEnemy19.getCategory())); // Create new enemy
                    break;
                case 20: // Minas Morgul
                    NPC randomEnemy20 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy20.getName(), randomEnemy20.getHp(), randomEnemy20.getStrength(), randomEnemy20.getGold(), randomEnemy20.getCategory())); // Create new enemy
                    break;
                case 22: // Mordor
                    NPC randomEnemy22 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy22.getName(), randomEnemy22.getHp(), randomEnemy22.getStrength(), randomEnemy22.getGold(), randomEnemy22.getCategory())); // Create new enemy
                    break;
                case 23: // Plains of Gorgoroth
                    NPC randomEnemy23 = normalEnemies.get(random.nextInt(normalEnemies.size()));
                    encounterEnemy(new NPC(randomEnemy23.getName(), randomEnemy23.getHp(), randomEnemy23.getStrength(), randomEnemy23.getGold(), randomEnemy23.getCategory())); // Create new enemy
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Handles an encounter with a random enemy at the current location.
     *
     * @param enemy The enemy to encounter.
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    private void encounterEnemy(NPC enemy) throws FileNotFoundException, InterruptedException {
        Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/normalenemy.wav");
        System.out.println("⚔\uFE0F An enemy appears! " + enemy.getName() + " is blocking your path!");
        if (battle(enemy)) {
            currentLocationIndex++;
            currentEnemy = null;
            if (currentLocationIndex < locations.size()) {
                postBattleMenu();
            }
        } else {
            if (hero.getHp() <= 0) {
                Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/death.wav");
                System.out.println("You have been defeated! Game Over.");
                System.out.println();
                Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/death.txt");
                sleep(5000);
                System.exit(0);
            }
        }
    }

    /**
     * Handles an encounter with a mini-boss at specific locations.
     *
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    private void encounterMiniBoss() throws FileNotFoundException, InterruptedException {
        if (miniBosses.isEmpty()) {
            System.out.println("\uD83E\uDD37 No mini-boss remains... This path is empty.");
            currentLocationIndex++;
            navigateLocation();
            return;
        }

        NPC miniBoss = miniBosses.remove(0);
        Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/minibossfight.wav");
        System.out.println("\n\uD83D\uDC79 A Mini-Boss appears: " + miniBoss.getName() + "!");
        if (battle(miniBoss)) {
            currentLocationIndex++;
            if (currentLocationIndex < locations.size()) {
                postBattleMenu();
            }
        } else {
            if (hero.getHp() <= 0) {
                Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/death.wav");
                System.out.println("You have been defeated! Game Over.");
                System.out.println();
                Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/death.txt");
                sleep(5000);
                System.exit(0);
            }
        }
    }

    /**
     * Handles the post-battle menu, allowing the player to use consumables before advancing.
     *
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    private void postBattleMenu() throws FileNotFoundException, InterruptedException {
        while (true) {
            Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/postbattle.wav");
            System.out.println("\nPost-Battle Options:");
            System.out.println("❤️ HP: " + hero.getHp() + "/" + hero.getMaxHp() + " | 💪 Strength: " + hero.getStrength() + " | 💰 Gold: " + hero.getMoney() + " \uD83E\uDE99");
            System.out.println("[1] Use a Consumable");
            System.out.println("[2] Continue Journey");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    useConsumable(null);
                    break;
                case 2:
                    navigateLocation();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Allows the hero to use a consumable item from their inventory.
     *
     * @param enemy The current enemy in battle, or null if used outside combat.
     */
    private void useConsumable(NPC enemy) {
        if (hero.getInventory().isEmpty()) {
            System.out.println("❌ You have no consumables in your inventory!");
            return;
        }
        System.out.println("\nYour Consumables:");
        int consumableCount = 0;
        for (int i = 0; i < hero.getInventory().size(); i++) {
            Item item = hero.getInventory().get(i);
            if (item instanceof Consumable && !(item instanceof CombatConsumable)) {
                Consumable consumable = (Consumable) item;
                System.out.println("[" + (consumableCount + 1) + "] " + consumable.getName() + " (" + consumable.getEffectType() + " effect: " + consumable.getEffectValue() + ")");
                consumableCount++;
            }
        }
        if (consumableCount == 0) {
            System.out.println("You don't have any consumables!");
            return;
        }
        System.out.print("Enter the number of the consumable to use (or 0 to go back): ");
        int consumableChoice = scanner.nextInt();
        scanner.nextLine();
        if (consumableChoice > 0 && consumableChoice <= consumableCount) {
            Item selectedItem = null;
            int currentIndex = 0;

            for (int i = 0; i < hero.getInventory().size(); i++) {
                Item item = hero.getInventory().get(i);
                if (item instanceof Consumable && !(item instanceof CombatConsumable)) {
                    if (currentIndex == consumableChoice - 1) {
                        selectedItem = item;
                        break;
                    }
                    currentIndex++;
                }
            }
            if (selectedItem instanceof Consumable && !(selectedItem instanceof CombatConsumable)) {
                Consumable selectedConsumable = (Consumable) selectedItem;
                System.out.println("\n" + hero.getName() + " used " + selectedConsumable.getName() + "!");
                selectedConsumable.use(hero);
                hero.getInventory().remove(selectedItem);
            }
        } else if (consumableChoice == 0) {
            return;
        } else {
            System.out.println("Invalid choice.");
        }
    }

    /**
     * Handles the battle logic between the hero and an enemy.
     *
     * @param enemy The enemy NPC the hero is fighting.
     * @return True if the hero wins the battle, false if the hero is defeated.
     */
    public boolean battle(NPC enemy) {
        int damage;
        boolean isStunned = false;
        boolean specialAttackUsed = false;
        List<CombatConsumable> activeEffects = new ArrayList<>();
        Random random = new Random();

        while (hero.getHp() > 0 && enemy.getHp() > 0) {
            System.out.println("\n" + hero.getName() + "'s HP: " + hero.getHp() + " ❤️ || " + enemy.getName() + "'s HP: " + enemy.getHp() + " ❤️");
            System.out.println("Choose your action:");
            System.out.println("[1] Normal Attack");
            if (!specialAttackUsed) {
                System.out.println("[2] Special Attack (One-time per fight - DMG x2)");
            } else {
                System.out.println(Tools.applyStrikethrough("[2] Special Attack (One-time per fight - DMG x2)"));
            }
            System.out.println("[3] Use a Consumable");
            System.out.println("[4] Use a Combat Consumable");
            System.out.println("[5] Attempt to Flee");
            System.out.println("[0] Exit Middle-Earth");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    exitMiddleEarth();
                    continue;
                case 1: // Normal Attack
                    damage = hero.getTotalAttackPower();
                    int normalAttackDamage = Math.min(damage, enemy.getHp());
                    enemy.takeDamage(normalAttackDamage);
                    System.out.println("\n" + hero.getName() + " attacks " + enemy.getName() + " for " + normalAttackDamage + " damage!");
                    if (hero.getMainWeapon() != null && "onHit".equals(hero.getMainWeapon().getSpecialEffectType())) {
                        if (random.nextDouble() < hero.getMainWeapon().getEffectChance()) {
                            MainWeapon weapon = hero.getMainWeapon();
                            if (weapon != null) {
                                if ("enemy".equals(weapon.getEffectTarget())) {
                                    weapon.applyWeaponEffect(enemy);
                                } else if ("self".equals(weapon.getEffectTarget())) {
                                    int healAmount = weapon.getEffectValue();
                                    if (hero.getHp() < hero.getMaxHp()) {
                                        hero.setHp(Math.min(hero.getHp() + healAmount, hero.getMaxHp()));
                                        System.out.println(weapon.getName() + " healed " + hero.getName() + " for " + healAmount + " HP!");
                                    }
                                }
                            }
                        }
                    }
                    if (!enemy.isDefeated() && !isStunned) {
                        int enemyDamage = enemy.getStrength();
                        hero.takeDamage(enemyDamage);
                        System.out.println(enemy.getName() + " strikes back for " + enemyDamage + " damage!");
                    }
                    isStunned = false;
                    applyConsumableEffects(enemy, activeEffects);
                    break;
                case 2: // Special Attack
                    if (!specialAttackUsed) {
                        damage = hero.getTotalAttackPower() * 2;
                        int specialAttackDamage = Math.min(damage, enemy.getHp());
                        enemy.takeDamage(specialAttackDamage);
                        System.out.println("\n" + hero.getName() + " unleashes a special attack on " + enemy.getName() + " for " + specialAttackDamage + " damage!");
                        if (hero.getMainWeapon() != null && "onHit".equals(hero.getMainWeapon().getSpecialEffectType())) {
                            if (random.nextDouble() < hero.getMainWeapon().getEffectChance()) {
                                MainWeapon weapon = hero.getMainWeapon();
                                if (weapon != null) {
                                    if ("enemy".equals(weapon.getEffectTarget())) {
                                        weapon.applyWeaponEffect(enemy);
                                    } else if ("self".equals(weapon.getEffectTarget())) {
                                        int healAmount = weapon.getEffectValue();
                                        if (hero.getHp() < hero.getMaxHp()) {
                                            hero.setHp(Math.min(hero.getHp() + healAmount, hero.getMaxHp()));
                                            System.out.println(weapon.getName() + " healed " + hero.getName() + " for " + healAmount + " HP!");
                                        }
                                    }
                                }
                            }
                        }
                        if (!enemy.isDefeated() && !isStunned) {
                            int enemyDamage = enemy.getStrength();
                            hero.takeDamage(enemyDamage);
                            System.out.println(enemy.getName() + " strikes back for " + enemyDamage + " damage!");
                        }
                        specialAttackUsed = true;
                        isStunned = false;
                        applyConsumableEffects(enemy, activeEffects);
                    } else {
                        System.out.println("You have already used your special attack!");
                    }
                    break;
                case 3:
                    if (hero.getInventory().isEmpty()) {
                        System.out.println("❌ You have no consumables in your inventory!");
                        continue;
                    }
                    System.out.println("\nYour Consumables:");
                    int consumableCount = 0;
                    for (int i = 0; i < hero.getInventory().size(); i++) {
                        Item item = hero.getInventory().get(i);
                        if (item instanceof Consumable &&!(item instanceof CombatConsumable)) {
                            Consumable consumable = (Consumable) item;
                            System.out.println("[" + (consumableCount + 1) + "] " + consumable.getName() + " (" + consumable.getEffectType() + " effect: " + consumable.getEffectValue() + ")");
                            consumableCount++;
                        }
                    }
                    if (consumableCount == 0) {
                        System.out.println("You don't have any consumables!");
                        continue;
                    }
                    System.out.print("Enter the number of the consumable to use (or 0 to go back): ");
                    int consumableChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (consumableChoice > 0 && consumableChoice <= consumableCount) {
                        Item selectedItem = null;
                        int currentIndex = 0;
                        for (int i = 0; i < hero.getInventory().size(); i++) {
                            Item item = hero.getInventory().get(i);
                            if (item instanceof Consumable &&!(item instanceof CombatConsumable)) {
                                if (currentIndex == consumableChoice - 1) {
                                    selectedItem = item;
                                    break;
                                }
                                currentIndex++;
                            }
                        }
                        if (selectedItem instanceof Consumable) {
                            Consumable selectedConsumable = (Consumable) selectedItem;
                            System.out.println("\n" + hero.getName() + " used " + selectedConsumable.getName() + "!");
                            selectedConsumable.use(hero);
                            hero.getInventory().remove(selectedItem);

                            if (!enemy.isDefeated() &&!isStunned) {
                                int enemyDamage = enemy.getStrength();
                                hero.takeDamage(enemyDamage);
                                System.out.println(enemy.getName() + " strikes back for " + enemyDamage + " damage!");
                            }
                            isStunned = false;
                            applyConsumableEffects(enemy, activeEffects);
                        }
                    } else if (consumableChoice!= 0) {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 4:
                    if (hero.hasCombatConsumables()) {
                        System.out.println("\nYour Combat Consumables:");
                        int combatConsumableCount = 0;
                        ArrayList<CombatConsumable> combatConsumables = new ArrayList<>();
                        for (int i = 0; i < hero.getInventory().size(); i++) {
                            Item item = hero.getInventory().get(i);
                            if (item instanceof CombatConsumable) {
                                CombatConsumable c = (CombatConsumable) item;
                                System.out.println("[" + (combatConsumableCount + 1) + "] " + c.getName() + " (" + c.getEffectType() + " target - " + c.getEffectValue() + " DMG for " + c.getDuration() + " turn)");
                                combatConsumables.add(c);
                                combatConsumableCount++;
                            }
                        }

                        if (combatConsumableCount == 0) {
                            System.out.println("❌ You have no combat consumables in your inventory!");
                            continue;
                        }

                        System.out.print("Enter the number of the combat consumable to use (or 0 to go back): ");
                        int combatConsumableChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (combatConsumableChoice > 0 && combatConsumableChoice <= combatConsumableCount) {
                            CombatConsumable selectedConsumable = combatConsumables.get(combatConsumableChoice - 1);

                            System.out.println("\n" + hero.getName() + " used " + selectedConsumable.getName() + " on " + enemy.getName() + "!");
                            selectedConsumable.use(enemy);
                            if (selectedConsumable.getName().equals("🪨 Hobbit stone")) {
                                System.out.println(selectedConsumable.getName() + " " + selectedConsumable.getEffectType() + " " + enemy.getName() + " for " + selectedConsumable.getEffectValue() + " damage!");
                            } else {
                                System.out.println(selectedConsumable.getName() + " " + selectedConsumable.getEffectType() + " " + enemy.getName());
                            }
                            hero.getInventory().remove(selectedConsumable);

                            if (selectedConsumable.getStuns()) {
                                isStunned = true;
                            }

                            if (selectedConsumable.getDuration() > 0) {
                                activeEffects.add(selectedConsumable);
                            }


                            if (!enemy.isDefeated() && !isStunned) {
                                int enemyDamage = enemy.getStrength();
                                hero.takeDamage(enemyDamage);
                                System.out.println(enemy.getName() + " strikes back for " + enemyDamage + " damage!");
                            }
                            isStunned = false;
                            applyConsumableEffects(enemy, activeEffects);

                        } else if (combatConsumableChoice == 0) {
                            continue;
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("You don't have any combat consumables!");
                    }
                    break;
                case 5:
                    if (attemptFlee(enemy)) {
                        return true;
                    } else {
                        int enemyDamage = enemy.getStrength();
                        hero.takeDamage(enemyDamage);
                        System.out.println(enemy.getName() + " strikes " + hero.getName() + " for " + enemyDamage + " damage!");
                    }
                    isStunned = false;
                    applyConsumableEffects(enemy, activeEffects);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
            if (enemy.isDefeated()) {
                hero.setMoney(hero.getMoney() + enemy.getGold());
                System.out.println(hero.getName() + " has defeated " + enemy.getName() + "! (Loot: " + enemy.getGold() + " \uD83E\uDE99)");
                return true;
            }
            if (hero.getHp() <= 0) {
                return false;
            }
        }
        return false;
    }

    /**
     * Applies effects from combat consumables to the enemy.
     *
     * @param enemy          The enemy affected by the consumables.
     * @param activeEffects  A list of currently active combat consumable effects.
     */
    private void applyConsumableEffects(NPC enemy, List<CombatConsumable> activeEffects) {
        List<CombatConsumable> toRemove = new ArrayList<>();
        for (CombatConsumable effect : activeEffects) {
            effect.applyEffect(enemy);
            if (effect.getDuration() <= 0) {
                toRemove.add(effect);
            }
        }
        activeEffects.removeAll(toRemove);
    }

    /**
     * Attempts to flee from battle.
     *
     * @param enemy The enemy the hero is trying to flee from.
     * @return True if the hero successfully flees, false otherwise.
     */
    private boolean attemptFlee(NPC enemy) {
        System.out.println("\n🏃💨 " + hero.getName() + " tries to flee...");
        boolean canFlee = random.nextBoolean(); // 50% chance to succeed

        if (canFlee) {
            System.out.println("✅ Success! " + hero.getName() + " escapes!");
            return true;
        } else {
            System.out.println("❌ Failed! " + enemy.getName() + " lands a free attack!");
            return false;
        }
    }

    /**
     * Initiates the final boss battle against Sauron.
     *
     * @throws FileNotFoundException If an ASCII art file is missing.
     * @throws InterruptedException  If the game thread is interrupted.
     */
    public void finalBossBattle() throws FileNotFoundException, InterruptedException {
        Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/sauronfight.wav");
        System.out.println("\n\uD83D\uDD25 The fires of Mount Doom crackle around you...");
        System.out.println("\uD83C\uDFF0 The Dark Tower of Barad-dûr looms above...");
        System.out.println("⚫ Sauron, the Dark Lord, stands before you!");

        NPC sauron = new NPC("⚫ Sauron", 500, 33, 1000, "legendaryEnemy");
        battle(sauron);

        if (hero.getHp() > 0) {
            Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/final.wav");
            System.out.println("\uD83D\uDC51 **Middle-earth is free once more!**");
            System.out.println("\uD83C\uDF05 You leave Mordor, forever remembered as a legend.");
            System.out.println();
            Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/final.txt");
            sleep(10000);
            System.exit(0);
        } else {
            Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/death.wav");
            System.out.println("\n\uFE0F " + hero.getName() + " was slain... Middle-earth falls into darkness...");
            System.out.println();
            Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/death.txt");
            sleep(5000);
        }
        System.exit(0);
    }

    /**
     * Allows the Hero to leave the Adventure with a confirmation message
     */
    private void exitMiddleEarth() {
        System.out.println("🚪 " + hero.getName() + ", are you sure you want to leave your adventure?");
        System.out.print("[1] Yes, leave the adventure 🏕️\n[2] No, continue exploring 🏰\nYour choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("\n🌅 " + hero.getName() + " steps out of the adventure...");
            System.out.println("🏕️ The journey is over, but more adventures await in Middle-earth!");
            System.out.println("🎭 Thank you for playing!");
            System.exit(0);
        } else if (choice == 2) {
            System.out.println("\n🏰 " + hero.getName() + " chooses to stay and face the unknown...");
            return;
        } else {
            System.out.println("❌ Invalid choice! Please select a valid option.");
            exitMiddleEarth();
        }
    }
}
