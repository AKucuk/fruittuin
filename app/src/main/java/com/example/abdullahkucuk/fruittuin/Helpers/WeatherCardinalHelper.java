package com.example.abdullahkucuk.fruittuin.Helpers;

/**
 * Created by abdullah.kucuk on 6-11-2016.
 */

public class WeatherCardinalHelper {
    public enum WindDirection {
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST,
        NORTH_WEST
    }

    public static WindDirection getWindDirection(int degree) {
        return getWindDirection((double) degree);
    }
    public static WindDirection getWindDirection(double degree) {
        if(degree < 0){
            degree *= -1;
        }

        int index = (int)Math.round((  ((double)degree % 360) / 45)) % 8;
        return WindDirection.values()[index];
    }
}
