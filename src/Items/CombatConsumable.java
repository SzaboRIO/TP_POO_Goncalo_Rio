package Items;

public class CombatConsumable extends Consumable {
    public CombatConsumable(String name, String effect, int potency) {
        super(name, effect, potency);
    }

    public String getEffect() {
        return effect;
    }
}
