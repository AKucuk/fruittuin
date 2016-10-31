package com.example.abdullahkucuk.fruittuin.Fragments.Subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeurtochtPart2Fragment extends Fragment {

    UserModel userModel;

    public SpeurtochtPart2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speurtocht_part2, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userModel = bundle.getParcelable("userModel");
        }

        return view;
    }

}
