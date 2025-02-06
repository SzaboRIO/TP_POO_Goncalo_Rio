package Items;

import java.util.ArrayList;

public abstract class ItemHero {
    protected String name;
    protected int price;
    protected ArrayList<String> allowedHeroes;

    public ItemHero(String name, int price) {
        this.name = name;
        this.price = price;
        this.allowedHeroes = new ArrayList<>();
    }

    public void showDetails() {
        System.out.println("🛡️ Item: " + name);
        System.out.println("💰 Price: " + price + " gold");
        System.out.println("⚔️ Allowed Heroes: " + (allowedHeroes.isEmpty() ? "All" : allowedHeroes));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getAllowedHeroes() {
        return allowedHeroes;
    }
}
