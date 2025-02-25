package Items;

/**
 * Represents a generic item in the game.
 */
public class Item {
    protected final String name;
    protected final int cost;

    /**
     * Constructs an item with a specified name and cost.
     *
     * @param name The name of the item.
     * @param cost The cost of the item.
     */
    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The cost of the item.
     */
    public int getCost() {
        return cost;
    }
}
