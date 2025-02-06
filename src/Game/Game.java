package Game;

import Entities.*;
import Items.MainWeapon;
import java.util.Scanner;

public class Game {

    public static Hero createCharacter() {
        Scanner scanner = new Scanner(System.in);

        // Ask for the player's hero name
        System.out.print("🏷️ Enter your hero's name: ");
        String heroName = scanner.nextLine();

        // Choose hero class (keeps asking until valid)
        String heroEmoji = "";
        int classChoice;
        do {
            System.out.println("🧙 Welcome, traveler! Choose your hero class:");
            System.out.println("⚔️ [1] Warrior (Gondorian Knight)");
            System.out.println("🧙‍♂️ [2] Mage (Istari Wizard)");
            System.out.println("🏹 [3] Archer (Elven Ranger)");
            System.out.print("Your choice: ");
            classChoice = scanner.nextInt();

            switch (classChoice) {
                case 1 -> heroEmoji = "⚔️";
                case 2 -> heroEmoji = "🧙‍♂️";
                case 3 -> heroEmoji = "🏹";
                default -> System.out.println("❌ Invalid choice! Please select a valid hero class.");
            }
        } while (classChoice < 1 || classChoice > 3);

        // Choose difficulty
        System.out.println("🎚️ Choose your difficulty:");
        System.out.println("🟢 [1] Easy (More points, more gold)");
        System.out.println("🔴 [2] Hard (Fewer points, less gold)");
        System.out.print("Your choice: ");
        int difficultyChoice = scanner.nextInt();

        int creationPoints = (difficultyChoice == 1) ? 300 : 220;
        int gold = (difficultyChoice == 1) ? 20 : 15;

        System.out.println("✨ You have " + creationPoints + " points to distribute.");
        System.out.println("❤️ 1 HP = 1 point | 💪 1 Strength = 5 points");

        int hp = 0, strength = 0;

        // Ask for HP allocation
        System.out.print("Enter points for HP: ");
        int hpPoints = scanner.nextInt();
        while (hpPoints > creationPoints) {
            System.out.println("❌ Not enough points! You only have " + creationPoints + " left.");
            System.out.print("Enter points for HP: ");
            hpPoints = scanner.nextInt();
        }

        hp += hpPoints;
        creationPoints -= hpPoints;

        // Auto-calculate remaining Strength points
        int maxStrengthPoints = creationPoints / 5;
        if (maxStrengthPoints > 0) {
            System.out.println("💡 You have " + creationPoints + " points left.");
            System.out.println("💪 This allows for " + maxStrengthPoints + " points in Strength.");
            System.out.print("Would you like to allocate all remaining points to Strength? (Y/N): ");
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice == 'Y') {
                strength += maxStrengthPoints;
                creationPoints -= maxStrengthPoints * 5;
            } else {
                System.out.print("Enter points for Strength: ");
                int strengthPoints = scanner.nextInt();
                while (strengthPoints * 5 > creationPoints) {
                    System.out.println("❌ Not enough points! You only have " + creationPoints + " left.");
                    System.out.print("Enter points for Strength: ");
                    strengthPoints = scanner.nextInt();
                }
                strength += strengthPoints;
                creationPoints -= (strengthPoints * 5);
            }
        }

        System.out.println("✅ Character creation complete! Your stats:");
        System.out.println("❤️ HP: " + hp + " | 💪 Strength: " + strength + " | 💰 Gold: " + gold);

        // Assign a basic weapon to the hero
        MainWeapon starterWeapon = new MainWeapon("Basic Sword", 0, 5, 10);

        // Assign hero based on class choice
        Hero hero;
        if (classChoice == 1) {
            hero = new Warrior(heroEmoji + " " + heroName, hp, strength, gold, starterWeapon);
        } else if (classChoice == 2) {
            hero = new Mage(heroEmoji + " " + heroName, hp, strength, gold, starterWeapon);
        } else {
            hero = new Archer(heroEmoji + " " + heroName, hp, strength, gold, starterWeapon);
        }

        System.out.println(hero != null ? hero.getName() : "Unknown Hero" + " is ready for adventure!");
        return hero;
    }

    public static void startAdventure(Hero hero) {
        System.out.println("🏰 Welcome to Middle-earth, " + hero != null ? hero.getName() : "Unknown Hero" + "!");
        System.out.println("You must navigate the Labyrinth of Doom to reach victory!");
        System.out.println("Your journey begins now...\n");
    }

    public static void main(String[] args) {
        Hero hero = createCharacter();
        Labyrinth labyrinth = new Labyrinth(hero);
        labyrinth.start();
    }
}