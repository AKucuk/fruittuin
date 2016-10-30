package com.example.abdullahkucuk.fruittuin.Fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FruittuinVanWestFragment extends Fragment {


    public FruittuinVanWestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fruittuin_van_west, container, false);
    }

}
