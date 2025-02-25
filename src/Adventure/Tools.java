package Adventure;

/**
 * The Tools class provides utility methods for text manipulation and formatting
 * that can be used throughout the Adventure game.
 */
public class Tools {

    /**
     * Applies a strikethrough effect to the provided text using Unicode characters.
     * This method appends the Unicode strikethrough character (U+0336) after each
     * character in the original text, creating a visual strikethrough effect when displayed.
     *
     * @param text The original text to be formatted with strikethrough
     * @return A new string with strikethrough formatting applied
     */
    public static String applyStrikethrough(String text) {
        StringBuilder strikethroughText = new StringBuilder();
        for (char c : text.toCharArray()) {
            strikethroughText.append(c).append("\u0336");
        }
        return strikethroughText.toString();
    }
}