package Items;

import java.util.ArrayList;

/**
 * Abstract base class for representing an item that can be used by heroes.
 * This class defines common properties and functionalities for all hero items,
 * such as name, price, and a list of heroes who can use the item.
 */
public abstract class ItemHero {

    protected String name;
    protected int price;
    protected ArrayList<String> allowedHeroes;

    /**
     * Constructs a new ItemHero object.
     *
     * @param name  The name of the item.
     * @param price The price of the item.
     */
    public ItemHero(String name, int price) {
        this.name = name;
        this.price = price;
        this.allowedHeroes = new ArrayList<>();
    }

    /**
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Returns a list of hero names that are allowed to use this item.
     *
     * @return An ArrayList of hero names.
     */
    public ArrayList<String> getAllowedHeroes() {
        return allowedHeroes;
    }
}