package com.safetyzone.safetyzone;

import java.util.Random;

/**
 * Created by Zak on 31/10/2015.
 */
public class SafeService {
    public SafeService() {
        // Do nothing inside the constructor just yet
    }
    public int getSafetyRating(double longitude, double latitude) {
        return new Random().nextInt(3);
    }
}
