package com.example.abdullahkucuk.fruittuin.Global;

import java.util.UUID;

/**
 * Created by pplom on 19-12-2016.
 */

public class Memory {

    private static Session instance = null;

    public Memory() {

    }

    public static Session getInstance() {

        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


}
