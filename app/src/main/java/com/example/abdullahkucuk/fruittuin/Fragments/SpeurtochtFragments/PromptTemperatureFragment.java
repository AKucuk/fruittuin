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

import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.WeatherHttpClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptTemperatureFragment extends Fragment {
    View view;
    Button btnTemperatuur;
    EditText txtTemperatuur;
    PromptTemperatureFragment promptTemperatureFragment;
    public float guessedTemperatuur;

    public PromptTemperatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_prompt_temperature, container, false);

        promptTemperatureFragment = this;

        txtTemperatuur = (EditText) view.findViewById(R.id.txtTemperatuur);
        btnTemperatuur = (Button) view.findViewById(R.id.btnTemperatuur);
        btnTemperatuur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String input = txtTemperatuur.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul de temperatuur in...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                int temperatuur;
                try {
                    temperatuur = Integer.parseInt(input);
                    if (temperatuur >= 50) {
                        Toast.makeText(context, temperatuur + "?!?! Dat kan helemaal niet!", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    else if(temperatuur < -40) {
                        Toast.makeText(context, temperatuur + "??? Dat kan niet kouwe!", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                }
                catch (Exception exception) {
                    Toast.makeText(context, "Vul een getal in!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                guessedTemperatuur = temperatuur;

                new WeatherHttpClient(promptTemperatureFragment).execute("Amsterdam");
            }
        });

        return view;
    }

}
