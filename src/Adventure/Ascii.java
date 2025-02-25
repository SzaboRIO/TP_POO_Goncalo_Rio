package Adventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Ascii class provides functionality for displaying ASCII art from text files.
 * It supports displaying ASCII art in either a special gold color for game start screens
 * or in the default terminal color for other types of ASCII art.
 */
public class Ascii {

    /** ANSI color code for yellow-gold text used for the game start screen */
    private static final String YELLOW_GOLD = "\033[38;2;204;153;51m";

    /** ANSI code to reset text color back to the terminal default */
    private static final String RESET = "\033[0m"; // Reset to default color

    /**
     * Reads and prints the contents of a text file containing ASCII art.
     * If the file is named "gamestart.txt", the text will be displayed in a special
     * yellow-gold color. All other files will be displayed in the default terminal color.
     *
     * @param path The file path of the ASCII art text file to be displayed
     * @throws FileNotFoundException If the specified file cannot be found or accessed
     */
    public static void printFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));

        boolean isGameStart = path.endsWith("gamestart.txt");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (isGameStart) {
                System.out.println(YELLOW_GOLD + line + RESET);
            } else {
                System.out.println(line); // Default color for other files
            }
        }
        sc.close();
    }
}