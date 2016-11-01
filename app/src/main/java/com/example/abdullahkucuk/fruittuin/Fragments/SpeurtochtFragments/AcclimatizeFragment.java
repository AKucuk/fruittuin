package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcclimatizeFragment extends Fragment {

    View view;
    TextView txtTimer;
    Button btnAcclimatize;

    public AcclimatizeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_acclimatize, container, false);

        txtTimer = (TextView)view.findViewById(R.id.txtTimer);
        btnAcclimatize = (Button) view.findViewById(R.id.btnAcclimatize);

        new CountDownTimer(30000, 1000)
        {
            public void onTick(long millisUntilFinished) {
                txtTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                txtTimer.setText("Done");
                btnAcclimatize.setVisibility(View.VISIBLE);
            }
        }.start();




        return view;
    }

}
