package com.example.abdullahkucuk.fruittuin.Factory;

import android.support.v4.app.Fragment;

import com.example.abdullahkucuk.fruittuin.Enumerations.FragmentEnum;
import com.example.abdullahkucuk.fruittuin.Fragments.ContactFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.FruittuinVanWestFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.OpeningstijdenFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.PlattegrondFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.StartFragment;

/**
 * Created by Kucuk on 19-1-2017.
 */

public class FragmentManager {
    private static Fragment startFragment = null;
    private static Fragment fruittuinVanWestFragment = null;
    private static Fragment plattegrondFragment = null;
    private static Fragment contactFragment = null;
    private static Fragment openingstijdenFragment = null;

    public FragmentManager() {

    }


    public Fragment getFragment(FragmentEnum fragmentEnum) {
        switch (fragmentEnum) {
            case START:
                return getStartFragment();
            case FRUITTUIN_VAN_WEST:
                return getFruittuinVanWestFragment();
            case PLATTEGROND:
                return getPlattegrondFragment();
            case CONTACT:
                return getContactFragment();
            case OPENINGSTIJDEN:
                return getOpeningstijdenFragment();
            default:
                break;
        }
        return null;
    }

    private Fragment getStartFragment() {
        if(startFragment == null) {
            startFragment = new StartFragment();
        }
        return startFragment;
    }

    public Fragment getFruittuinVanWestFragment() {
        if(fruittuinVanWestFragment == null) {
            fruittuinVanWestFragment = new FruittuinVanWestFragment();
        }
        return fruittuinVanWestFragment;    }

    public Fragment getPlattegrondFragment() {
        if(plattegrondFragment == null) {
            plattegrondFragment = new PlattegrondFragment();
        }
        return plattegrondFragment;    }

    public Fragment getContactFragment() {
        if(contactFragment == null) {
            contactFragment = new ContactFragment();
        }
        return contactFragment;    }

    public Fragment getOpeningstijdenFragment() {
        if(openingstijdenFragment == null) {
            openingstijdenFragment = new OpeningstijdenFragment();
        }
        return openingstijdenFragment;
    }

}
