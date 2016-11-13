package com.example.abdullahkucuk.fruittuin.Models;

/**
 * Created by abdullah.kucuk on 12-11-2016.
 */

public class LuisEntityModel {
    private String entity;
    private String type;
    private int startIndex;
    private int endIndex;
    private double score;

    public LuisEntityModel(String entity, String type, int startIndex, int endIndex, double score) {
        this.entity = entity;
        this.type = type;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.score = score;
    }


    public String getEntity() {
        return entity;
    }

    public String getType() {
        return type;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public double getScore() {
        return score;
    }
}
