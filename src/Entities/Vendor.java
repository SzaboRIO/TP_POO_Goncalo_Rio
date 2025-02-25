package Entities;

import Adventure.Tools;
import Items.CombatConsumable;
import Items.Consumable;
import Items.HealthPotion;
import Items.Item;
import Items.MainWeapon;
import Items.MaxHpPotion;
import Items.StrengthPotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The Vendor class represents a merchant that sells various items to heroes in the game.
 * The vendor maintains a collection of all available items and can provide a random selection
 * of these items for purchase by heroes. Items include various consumables, combat items,
 * and weapons with different attributes and effects.
 */
public class Vendor {
    /** A list containing all available items the vendor can potentially sell */
    private final List<Item> allItems = new ArrayList<>();
    /** Random number generator used to select random items for display */
    private final Random random = new Random();

    /**
     * Constructor for the Vendor class.
     * Initializes the vendor with a comprehensive inventory of items including:
     * - Health, Strength, and MaxHp potions
     * - Combat consumables with various effects
     * - Weapons categorized as swords, bows, and staves with special effects
     *
     * Each item is created with specific attributes such as name, effect type,
     * effect value, cost, and other properties relevant to its category.
     */
    public Vendor() {
        // Consumables
        allItems.add(new HealthPotion("🍞 Lembas Bread", 30, 5));
        allItems.add(new HealthPotion("🌿 Athelas (Kingsfoil)", 50, 8));
        allItems.add(new StrengthPotion("🧪 Miruvor", 5, 100));
        allItems.add(new StrengthPotion("🍷 Dorwinion Wine", 3, 80));
        allItems.add(new MaxHpPotion("🍵 Ent-draught", 10, 60));
        allItems.add(new MaxHpPotion("🍪 Waybread of the Dúnedain", 7, 40));

        // Combat Consumables
        allItems.add(new CombatConsumable("🪨 Hobbit stone", "Stuns", 10, 5, true,1));
        allItems.add(new CombatConsumable("🔥 Fire Oil", "Burns", 10, 15, false,3));
        allItems.add(new CombatConsumable("☠️ Morgul Blade", "Poisons", 9, 25, false,5));

        // Weapons - Swords
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Andúril – Flame of the West", "sword", 25, "🔥 Burns enemies", 100, "onHit", 0.4, "enemy", 20));
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Narsil", "sword", 22, "⚔️ Strong vs. Legendary Enemies", 80, "onHit", 0.4, "enemy", 16));
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Sting", "sword", 18, "💥 Strong vs. Orc Kind", 50, "onHit", 0.3, "enemy", 10));
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Glamdring – The Foe-Hammer", "sword", 20, "💥 Strong vs. Orc Kind", 70, "onHit", 0.5, "enemy", 14));
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Orcrist – The Goblin-Cleaver", "sword", 19, "💥 Strong vs. Orc Kind", 60, "onHit", 0.4, "enemy", 12));
        allItems.add(new MainWeapon("\uD83D\uDDE1\uFE0F Herugrim", "sword", 21, "\uD83D\uDCAA\uD83D\uDC96 Enhances Strength and MaxHP", 75, "passive", 1, null, 8));

        // Weapons - Bows
        allItems.add(new MainWeapon("\uD83C\uDFF9 The Bow of Galadhrim", "bow", 24, "\uD83C\uDFAF Penetrates armor", 80, "onHit", 0.4, "enemy", 16));
        allItems.add(new MainWeapon("\uD83C\uDFF9 Legolas' Mirkwood Bow", "bow", 21, "💥 Strong vs. Orc Kind", 60, "onHit", 0.5, "enemy", 14));
        allItems.add(new MainWeapon("\uD83C\uDFF9 Belthronding", "bow", 25, "🔥 Burns enemies", 90, "onHit", 0.4, "enemy", 18));
        allItems.add(new MainWeapon("\uD83C\uDFF9 The Bow of Bard the Bowman", "bow", 22, "\uD83D\uDCAA Enhances Strength", 70,"passive", 1.0, null, 10));
        allItems.add(new MainWeapon("\uD83C\uDFF9 Aeglos' Blessing Bow", "bow", 22, "✨ Heals heroes", 70,"onHit", 0.4, "self", 10));

        // Weapons - Staves
        allItems.add(new MainWeapon("\uD83E\uDE84 Gandalf the Grey's Staff", "staff", 18, "\uD83D\uDCAA\uD83D\uDC96 Enhances Strength and MaxHP", 50, "passive", 1.0, null, 7));
        allItems.add(new MainWeapon("\uD83E\uDE84 Gandalf the White's Staff", "staff", 22, "\uD83D\uDCAA\uD83D\uDC96 Enhances Strength and MaxHP", 80, "passive", 1.0, null, 9));
        allItems.add(new MainWeapon("\uD83E\uDE84 Saruman's Staff", "staff", 21, "🌀 Confuses enemies", 70, "onHit", 0.4, "enemy", 0));
        allItems.add(new MainWeapon("\uD83E\uDE84 Radagast's Staff", "staff", 19, "✨ Heals heroes", 60, "onHit", 0.4, "self", 0));
        allItems.add(new MainWeapon("\uD83E\uDE84 Morgul Staff", "staff", 24, "🔥 Burns enemies", 100, "onHit", 0.4, "enemy", 5));
    }

    /**
     * Generates a random selection of items from the vendor's full inventory.
     *
     * This method selects up to 10 random items from the vendor's complete inventory.
     * If the selected item is either "Lembas Bread" or "Athelas (Kingsfoil)", its stock
     * is set to 3 units. Other items don't have limited stock by default.
     *
     * @return ArrayList<Item> A list containing randomly selected items for sale
     */
    public ArrayList<Item> getRandomInventory() {
        ArrayList<Item> selectedItems = new ArrayList<>();
        ArrayList<Item> tempAllItems = new ArrayList<>(allItems);

        while (selectedItems.size() < 10 &&!tempAllItems.isEmpty()) {
            int randomIndex = random.nextInt(tempAllItems.size());
            Item item = tempAllItems.remove(randomIndex);

            if (item instanceof HealthPotion healthPotion) {
                if ("🍞 Lembas Bread".equals(healthPotion.getName()) || "🌿 Athelas (Kingsfoil)".equals(healthPotion.getName())) {
                    healthPotion.setStock(3);
                }
            }
            selectedItems.add(item);
        }
        return selectedItems;
    }

    /**
     * Manages the transaction process between the vendor and a hero.
     *
     * This method handles the entire buying process including:
     * - Displaying the vendor's randomly selected inventory
     * - Showing item details (name, effect, cost)
     * - Processing the hero's purchase choices
     * - Applying appropriate restrictions (class-specific weapons, insufficient funds)
     * - Updating the hero's inventory and gold after purchases
     * - Managing item stocks, particularly for consumable items
     * - Tracking previously purchased items to prevent duplicate purchases in a session
     *
     * The method continues until the hero chooses to exit the vendor interface.
     *
     * @param hero The hero character who is buying items from the vendor
     */
    public void sellItem(Hero hero) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> inventory = getRandomInventory();
        ArrayList<Item> boughtThisSession = new ArrayList<>();

        boolean keepBuying = true;
        while (keepBuying) {
            System.out.println("\n\uD83D\uDED2 Vendor's inventory⤵\uFE0F || " + hero.getName() + "'s \uD83D\uDCB0 Gold: " + hero.getMoney() + " \uD83E\uDE99");

            ArrayList<Item> displayInventory = new ArrayList<>();

            for (Item item: inventory) {
                if (item instanceof MainWeapon &&!hero.getBoughtItems().contains(item)) {
                    displayInventory.add(item);
                } else if (!(item instanceof MainWeapon)) {
                    displayInventory.add(item);
                }
            }

            for (int i = 0; i < displayInventory.size(); i++) {
                Item item = displayInventory.get(i);
                String itemLine = "";

                if (item instanceof MainWeapon) {
                    MainWeapon weapon = (MainWeapon) item;
                    itemLine = "[" + (i + 1) + "] " + weapon.getName() + " (" + weapon.getType() + ", STR: " + weapon.getAttackPower() + ") - " + weapon.getSpecialEffect() + " * Cost: " + weapon.getCost() + " \uD83E\uDE99";
                } else if (item instanceof HealthPotion) {
                    HealthPotion healthPotion = (HealthPotion) item;
                    itemLine = "[" + (i + 1) + "] " + healthPotion.getName() + " (" + healthPotion.getEffectType() + " effect: " + healthPotion.getEffectValue() + ") - " + healthPotion.getStock() + " in stock * Cost: " + healthPotion.getCost() + " \uD83E\uDE99";
                } else if (item instanceof Consumable) {
                    Consumable consumable = (Consumable) item;
                    itemLine = "[" + (i + 1) + "] " + consumable.getName() + " (" + consumable.getEffectType() + " effect: " + consumable.getEffectValue() + ") * Cost: " + consumable.getCost() + " \uD83E\uDE99";
                } else if (item instanceof CombatConsumable) {
                    CombatConsumable combatConsumable = (CombatConsumable) item;
                    itemLine = "[" + (i + 1) + "] " + combatConsumable.getName() + " (" + combatConsumable.getEffectType() + " target - " + combatConsumable.getEffectValue() + " DMG for " + combatConsumable.getDuration() + " turn) * Cost: " + combatConsumable.getCost() + " \uD83E\uDE99";
                }
                if (boughtThisSession.contains(item) ||
                        (item instanceof HealthPotion && ((HealthPotion) item).getStock() == 0)) {
                    itemLine = Tools.applyStrikethrough(itemLine);
                }

                if (!itemLine.isEmpty()) {
                    System.out.println(itemLine);
                }

            }

            System.out.print("Enter item number to buy (or 0 to leave): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                keepBuying = false;

            } else if (choice > 0 && choice <= displayInventory.size()) {
                Item item = displayInventory.get(choice - 1);
                if (boughtThisSession.contains(item) ||
                        (item instanceof HealthPotion && ((HealthPotion) item).getStock() == 0)) {

                    if (boughtThisSession.contains(item)) {
                        System.out.println("❌ You already bought this item in this session!");
                    } else {
                        System.out.println("❌ This item is out of stock!");
                    }
                    continue;
                }

                if (item instanceof MainWeapon) {
                    MainWeapon weapon = (MainWeapon) item;
                    if (!weapon.canUse(hero)) {
                        System.out.println("❌ This weapon is not for your class!");
                    } else if (hero.getMoney() < weapon.getCost()) {
                        System.out.println("❌ You don't have enough money to buy this weapon!");
                    } else {
                        System.out.println("⚔️ You sell your old weapon and equip " + weapon.getName());
                        if (hero.getEquippedWeapon()!= null) {
                            hero.unequipWeapon(hero.getEquippedWeapon());
                        }
                        hero.setMoney(hero.getMoney() - (weapon.getCost() / 2));
                        hero.equipWeapon(weapon);
                        boughtThisSession.add(item);
                    }
                } else if (item instanceof Consumable) {
                    Consumable consumable = (Consumable) item;
                    if (hero.getMoney() < consumable.getCost()) {
                        System.out.println("❌ You don't have enough money to buy this consumable!");
                    } else {
                        System.out.println("🍞 You bought " + consumable.getName() + "!");
                        hero.setMoney(hero.getMoney() - consumable.getCost());

                        if (consumable instanceof HealthPotion) {
                            ((HealthPotion) consumable).setStock(((HealthPotion) consumable).getStock() - 1);
                        }

                        if (!(consumable instanceof HealthPotion) || ((HealthPotion) consumable).getStock() == 0) {
                            boughtThisSession.add(item);
                        }

                        hero.addItemToInventory(consumable);
                    }
                } else if (item instanceof CombatConsumable) {
                    CombatConsumable combatConsumable = (CombatConsumable) item;
                    if (hero.getMoney() < combatConsumable.getCost()) {
                        System.out.println("❌ You don't have enough money to buy this combat consumable!");
                    } else {
                        System.out.println("You bought " + combatConsumable.getName() + "!");
                        hero.setMoney(hero.getMoney() - combatConsumable.getCost());
                        hero.addItemToInventory(combatConsumable);
                        boughtThisSession.add(item);
                    }
                }
            } else {
                System.out.println("❌ Invalid choice. Please enter a number from the menu.");
            }
        }
    }
}