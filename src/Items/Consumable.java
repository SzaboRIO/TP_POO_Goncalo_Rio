package Items;

public class Consumable {
    private final String name;
    private final String effectType; // "heal", "strBoost", "hpBoost"
    private final int effectValue;

    public Consumable(String name, String effectType, int effectValue) {
        this.name = name;
        this.effectType = effectType;
        this.effectValue = effectValue;
    }

    public String getName() {
        return name;
    }

    public String getEffectType() {
        return effectType;
    }

    public int getEffectValue() {
        return effectValue;
    }
}
