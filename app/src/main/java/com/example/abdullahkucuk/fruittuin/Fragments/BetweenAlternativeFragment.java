package com.example.abdullahkucuk.fruittuin.Fragments;


import android.media.Image;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetweenAlternativeFragment extends Fragment {


    Button btnVolgende;
    ImageView imgFirst;
    ImageView imgSecond;
    TextView txtFirst;
    TextView txtSecond;
    private String textPartOne;
    private String textPartTwo;
    private int firstImageString;
    private int secondImageString;
    private Fragment fragment;

    public BetweenAlternativeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_between_alternative, container, false);
        // Inflate the layout for this fragment

        btnVolgende = (Button) view.findViewById(R.id.btnVolgende);
        imgFirst = (ImageView) view.findViewById(R.id.imgFirst);
        txtFirst = (TextView) view.findViewById(R.id.txtFirst);
        txtFirst.setText(textPartOne);
        imgSecond = (ImageView) view.findViewById(R.id.imgSecond);
        txtSecond = (TextView) view.findViewById(R.id.txtSecond);

        if (secondImageString > 0) {
            imgSecond.setImageResource(secondImageString);
        } else {
            imgSecond.setVisibility(View.INVISIBLE);
            imgSecond.setMaxHeight(0);
        }

        if (textPartTwo != null) {
            txtSecond.setText(textPartTwo);
        } else {
            txtSecond.setVisibility(View.INVISIBLE);
            txtSecond.setMaxHeight(0);
        }


        KeyboardHelper.hideKeyboard(getActivity());

        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragment() == null) {
                    return;
                }
                FragmentHelper.addFragment(getFragmentManager(), getFragment());
            }
        });

        return view;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTextPartOne() {
        return textPartOne;
    }

    public void setTextPartOne(String textPartOne) {
        this.textPartOne = textPartOne;
    }

    public int getSecondImageString() {
        return secondImageString;
    }

    public void setSecondImageString(int secondImageString) {
        this.secondImageString = secondImageString;
    }

    public int getFirstImageString() {
        return firstImageString;
    }

    public void setFirstImageString(int firstImageString) {
        this.firstImageString = firstImageString;
    }

    public String getTextPartTwo() {
        return textPartTwo;
    }

    public void setTextPartTwo(String textPartTwo) {
        this.textPartTwo = textPartTwo;
    }
}


