package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheEndFragment extends Fragment {


    public TheEndFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_the_end, container, false);

        KeyboardHelper.hideKeyboard(getActivity());

        return view;
    }

}
