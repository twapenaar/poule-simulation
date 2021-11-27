package nl.twapenaar.poulesimulation.services;

import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    public static void Log(String logMsg){
        Log.d("PouleSimulation", logMsg);
    }

    /**
     * return a random value between 0 and your range
     * @param highRange|int is inclusive
     * @return int
     */
    public static int RandomInt(int highRange){
        return RandomInt(0, highRange);
    }

    /**
     * return a random value between given low value and given high value
     * @param lowRange|int is inclusive
     * @param highRange|int is inclusive
     * @return int
     */
    public static int RandomInt(int lowRange, int highRange){
        return ThreadLocalRandom.current().nextInt(lowRange, highRange+1);

    }
}
