package com.example.abdullahkucuk.fruittuin.Models;

/**
 * Created by abdullah.kucuk on 16-10-2016.
 */
public class IntentModel {
    private String intent;
    private double score;

    public IntentModel(String intent, double score) {
        this.intent = intent;
        this.score = score;
    }

    public String getIntent() {
        return intent;
    }

    public double getScore() {
        return score;
    }
}
