package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClayFragment extends Fragment {

    Button btnNext;
    private Fragment fragment;

    public ClayFragment() {
        // Required empty public constructor
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clay, container, false);

        btnNext = (Button) view.findViewById(R.id.btnVolgende);

        btnNext.setOnClickListener(new View.OnClickListener() {
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

}
