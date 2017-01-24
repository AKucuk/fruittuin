package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.TimeHelper;
import com.example.abdullahkucuk.fruittuin.Models.LuisEntityModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.Luis;
import com.example.abdullahkucuk.fruittuin.Tasks.WindDirectionTask;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptWindDirectionFragment extends Fragment {
    View view;
    Button btnWindDirection;
    EditText txtWindDirection;
    TextView txtWindDirectionTimer;

    PromptWindDirectionFragment promptWindDirectionFragment;

    public PromptWindDirectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_prompt_wind_direction, container, false);

        promptWindDirectionFragment = this;

        btnWindDirection = (Button) view.findViewById(R.id.btnWindDirection);
        txtWindDirection = (EditText) view.findViewById(R.id.txtWindDirection);
        txtWindDirectionTimer = (TextView) view.findViewById(R.id.txtWindDirectionTimer);

        btnWindDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String windDirection = txtWindDirection.getText().toString();
                if(windDirection.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Vul in vanuit welke richting de wind waait!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                WindDirection wd = WindDirection.UNKNOWN;
                String[] richtingen = {"zuid", "noord", "oost", "west"};
                for (String richting : richtingen) {
                    if(windDirection.toLowerCase().contains(richting)) {
                        if(richting == "zuid")
                            wd = WindDirection.SOUTH;
                        if(richting == "noord")
                            wd = WindDirection.NORTH;
                        if(richting == "oost")
                            wd = WindDirection.EAST;
                        if(richting == "west")
                            wd = WindDirection.WEST;
                    }
                }

                if(wd == WindDirection.UNKNOWN) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ik heb je niet verstaan, typ je zin anders!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                new WindDirectionTask(promptWindDirectionFragment).execute(wd);
            }
        });

        //new CountDownTimer(30000, 1000)
        new CountDownTimer(3000, 1000)
        {
            public void onTick(long millisUntilFinished) {
                txtWindDirectionTimer.setText(TimeHelper.getTimeFormatByMilliseconds(millisUntilFinished));
            }

            public void onFinish() {
                txtWindDirectionTimer.setText("");
                txtWindDirection.setVisibility(View.VISIBLE);
                btnWindDirection.setVisibility(View.VISIBLE);
            }
        }.start();

        return view;
    }

}
