package com.example.abdullahkucuk.fruittuin.Models;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;

/**
 * Created by abdullah.kucuk on 13-11-2016.
 */

public class WindDirectionModel {
    private WindDirection windDirection;
    private boolean isWindDirection;

    public WindDirectionModel(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public WindDirectionModel(LuisEntityModel luisEntityModel) {
        if(!luisEntityModel.getType().startsWith("WindRichting::")) {
            isWindDirection = false;
            return;
        }

        switch(luisEntityModel.getEntity()) {
            case "noorden":
                windDirection = WindDirection.NORTH;
                break;
            case "noordoosten":
                windDirection = WindDirection.NORTH_EAST;
                break;
            case "westen":
                windDirection = WindDirection.WEST;
                break;
            case "zuidwesten":
                windDirection = WindDirection.SOUTH_WEST;
                break;
            case "zuiden":
                windDirection = WindDirection.SOUTH;
                break;
            case "zuidoosten":
                windDirection = WindDirection.SOUTH_EAST;
                break;
            case "noordwesten":
                windDirection = WindDirection.NORTH_WEST;
                break;
            case "oosten":
                windDirection = WindDirection.EAST;
                break;
            default:
                break;
        }
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public boolean isWindDirection() {
        return isWindDirection;
    }
}
