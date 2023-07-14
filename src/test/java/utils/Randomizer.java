package utils;

import java.util.Random;

public class Randomizer {
    public static int getRandomInt(){
        Random rand = new Random();
        int upperbound = 5000;
        return rand.nextInt(upperbound);
    }
}
