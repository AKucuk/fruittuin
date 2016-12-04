package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProefPaardenbloemFragment extends Fragment {

    TextView txtAnswerA;
    TextView txtAnswerB;
    TextView txtAnswerC;
    TextView txtAnswerD;
    Button btnNext;
    private Fragment fragment;

    public ProefPaardenbloemFragment() {
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
        View view = inflater.inflate(R.layout.fragment_proef_paardenbloem, container, false);


        txtAnswerA = (TextView) view.findViewById(R.id.txtAnswerA);
        txtAnswerB = (TextView) view.findViewById(R.id.txtAnswerB);
        txtAnswerC = (TextView) view.findViewById(R.id.txtAnswerC);
        txtAnswerD = (TextView) view.findViewById(R.id.txtAnswerD);

        txtAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Nee, dat klopt niet. De bladeren smaken toch niet zout", Toast.LENGTH_LONG)
                        .show();
                txtAnswerA.setTextColor(getResources().getColor(R.color.colorRed));
            }
        });
        txtAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Nee, dat klopt niet. De bladeren smaken toch niet zoet", Toast.LENGTH_LONG)
                        .show();
                txtAnswerB.setTextColor(getResources().getColor(R.color.colorRed));
            }
        });
        txtAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Goed zo. De bladeren smaken inderdaad bitter", Toast.LENGTH_LONG)
                        .show();
                txtAnswerC.setTextColor(getResources().getColor(R.color.colorGreen));
                btnNext.setVisibility(View.VISIBLE);

            }
        });
        txtAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Nee, dat klopt niet. De bladeren smaken toch niet zuur", Toast.LENGTH_LONG)
                        .show();
                txtAnswerD.setTextColor(getResources().getColor(R.color.colorRed));
            }
        });

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



