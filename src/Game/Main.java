package Game;

import Adventure.MiddleEarth;
import Entities.Hero;
import java.io.FileNotFoundException;
import static Game.Game.createCharacter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Hero hero = createCharacter();
        MiddleEarth middleEarth = new MiddleEarth(hero);
        middleEarth.start();
    }
}
