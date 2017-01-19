package com.example.abdullahkucuk.fruittuin.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.StartFragment;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceholderFragment extends Fragment {
    Fragment fragment = new StartFragment();

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public PlaceholderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentHelper.addFragment(getFragmentManager(), fragment);
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

}
