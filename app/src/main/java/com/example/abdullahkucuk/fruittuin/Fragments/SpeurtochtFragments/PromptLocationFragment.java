package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Tasks.LocationTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptLocationFragment extends Fragment {
    View view;
    Button btnLocatie;
    EditText txtLocatie;
    UserModel userModel;
    PromptLocationFragment promptLocationFragment;

    public PromptLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userModel = bundle.getParcelable("userModel");
        }

        promptLocationFragment = this;
        view = inflater.inflate(R.layout.fragment_prompt_location, container, false);

        KeyboardHelper.hideKeyboard(getActivity());

        btnLocatie = (Button) view.findViewById(R.id.btnTemperatuur);
        txtLocatie = (EditText) view.findViewById(R.id.txtTemperatuur);

        btnLocatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String locatie = txtLocatie.getText().toString().trim();
                if(locatie.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Vul je locatie in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                new LocationTask(promptLocationFragment).execute(locatie);
            }
        });

        return view;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
