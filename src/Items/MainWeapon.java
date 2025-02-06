package Items;

public class MainWeapon {
    private final String name;
    private final int attackPower;
    private final int cost;

    public MainWeapon(String name, int attackPower, int cost) {
        this.name = name;
        this.attackPower = attackPower;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
