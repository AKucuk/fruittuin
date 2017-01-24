package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetweenFragment extends Fragment {
    Button btnVolgende;
    private TextView txtInfo;
    private Fragment fragment;
    private String message;

    public BetweenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        View view = getView();


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_between, container, false);

        btnVolgende = (Button)view.findViewById(R.id.btnVolgende);
        txtInfo = (TextView)view.findViewById(R.id.txtInfo);
        txtInfo.setText(message);

        KeyboardHelper.hideKeyboard(getActivity());

        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragment() == null) {
                    return;
                }
                FragmentHelper.addFragment(getFragmentManager(), getFragment());
            }
        });

        return view;
    }

    public void setMessage(String text) {
        message = text;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
