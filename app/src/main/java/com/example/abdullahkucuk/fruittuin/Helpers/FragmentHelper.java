package com.example.abdullahkucuk.fruittuin.Helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.StartFragment;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * Created by abdullah.kucuk on 31-10-2016.
 */

public class FragmentHelper {

    public static Fragment mFragment;

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment) {
        mFragment = fragment;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
