package Game;

import Adventure.Ascii;
import Adventure.Audio;
import Entities.*;
import Items.MainWeapon;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Manages the core game functionality including character creation and initialization.
 * This class contains methods for starting a new game and setting up the player character.
 */
public class Game {

    /**
     * Creates and initializes a new player character through an interactive console interface.
     * Guides the user through the character creation process including:
     * <ul>
     *   <li>Naming their hero</li>
     *   <li>Selecting a hero class (Warrior, Mage, or Archer)</li>
     *   <li>Choosing game difficulty</li>
     *   <li>Distributing attribute points between HP and Strength</li>
     * </ul>
     *
     * The method includes comprehensive input validation to ensure all user inputs are valid.
     * Difficulty settings affect starting gold and available attribute points.
     * Each hero class receives an appropriate starter weapon.
     *
     * @return A fully initialized Hero object based on user selections
     * @throws FileNotFoundException If ASCII art files cannot be found or loaded
     */
    public static Hero createCharacter() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Ascii.printFile("TP_POO_Goncalo_Rio/AsciiFiles/gamestart.txt");
        Audio.playMusic("TP_POO_Goncalo_Rio/AudioFiles/gamestart.wav");

        System.out.print("\n🏷️ Enter your hero's name: ");
        String heroName = scanner.nextLine();

        String heroEmoji = "";
        int classChoice;
        do {
            System.out.println("\n\uD83D\uDD39 Welcome, traveler! Choose your hero class:");
            System.out.println("⚔️ [1] Warrior (Gondorian Knight)");
            System.out.println("🧙‍♂️ [2] Mage (Istari Wizard)");
            System.out.println("🏹 [3] Archer (Elven Ranger)");
            System.out.print("Your choice: ");
            try {
                classChoice = scanner.nextInt();
                scanner.nextLine();

                switch (classChoice) {
                    case 1 -> heroEmoji = "⚔️";
                    case 2 -> heroEmoji = "🧙‍♂️";
                    case 3 -> heroEmoji = "🏹";
                    default -> System.out.println("❌ Invalid choice! Please select a valid hero class.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
                classChoice = 0;
            }
        } while (classChoice < 1 || classChoice > 3);

        System.out.println("\n🎚️ Choose your difficulty:");
        System.out.println("[1]💚 Easy (More points, more gold)");
        System.out.println("[2]❤️ Hard (Fewer points, less gold)");
        System.out.print("Your choice: ");
        int difficultyChoice = 0;
        while (true) {
            try {
                difficultyChoice = scanner.nextInt();
                scanner.nextLine();
                if (difficultyChoice == 1 || difficultyChoice == 2) {
                    break;
                } else {
                    System.out.println("❌ Invalid choice. Please enter 1 or 2.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Invalid input.  Please enter a number.");
                scanner.nextLine();
            }
        }


        int totalPoints = (difficultyChoice == 1) ? 300 : 220;
        int gold = (difficultyChoice == 1) ? 20 : 15;

        int hp = 0, strength = 0;

        while (true) {
            System.out.println("\n✨ You have " + totalPoints + " points to distribute.");
            System.out.println("❤️ 1 HP = 1 point | 💪 1 Strength = 5 points");
            System.out.print("Enter points for HP: ");
            try {
                hp = scanner.nextInt();
                scanner.nextLine();
                if (hp > totalPoints) {
                    System.out.println("❌ You don't have enough points for that much HP!");
                } else if (hp <= 0) {
                    System.out.println("❌ HP must be greater than 0.");
                } else {
                    totalPoints -= hp;
                    break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("\n💡 You have " + totalPoints + " points left.");
            int maxStrength = totalPoints / 5;
            System.out.println("💪 This allows for " + maxStrength + " points in Strength.");
            System.out.print("Would you like to allocate all remaining points to Strength? (Y/N): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                strength = maxStrength;
                totalPoints = 0;
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                while(true){
                    System.out.print("Enter points for Strength: ");
                    try{
                        strength = scanner.nextInt();
                        scanner.nextLine();
                        if (strength * 5 > totalPoints) {
                            System.out.println("❌ You don't have enough points for that much Strength!");
                        } else if (strength < 0) {
                            System.out.println("❌ Strength cannot be negative.");
                        }
                        else {
                            totalPoints -= (strength * 5);
                            hp += totalPoints;
                            totalPoints = 0;
                            break;
                        }
                    }catch (java.util.InputMismatchException e) {
                        System.out.println("❌ Invalid input. Please enter a number.");
                        scanner.nextLine();
                    }
                }
                break;
            } else {
                System.out.println("❌ Invalid choice. Please enter Y or N.");
            }
        }

        System.out.println("\n✅ Character creation complete! Your stats:");
        System.out.println("❤️ HP: " + hp + " | 💪 Strength: " + strength + " | 💰 Gold: " + gold);

        MainWeapon starterWeaponSword = new MainWeapon("Rusty Sword", "sword", 5, "No effect", 0);
        MainWeapon starterWeaponBow = new MainWeapon("Wood Bow", "bow", 5, "No effect", 0);
        MainWeapon starterWeaponStaff = new MainWeapon("Wood Staff", "staff", 5, "No effect", 0);

        Hero hero;
        if (classChoice == 1) {
            hero = new Warrior(heroEmoji + " " + heroName, hp, strength, gold, starterWeaponSword);
        } else if (classChoice == 2) {
            hero = new Mage(heroEmoji + " " + heroName, hp, strength, gold, starterWeaponStaff);
        } else {
            hero = new Archer(heroEmoji + " " + heroName, hp, strength, gold, starterWeaponBow);
        }

        return hero;
    }
}