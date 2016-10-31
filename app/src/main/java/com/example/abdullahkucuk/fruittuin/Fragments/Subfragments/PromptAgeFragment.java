package com.example.abdullahkucuk.fruittuin.Fragments.Subfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptAgeFragment extends Fragment {

    PromptAgeFragment promptAgeFragment;
    UserModel userModel;
    Button btnPromptAge;
    EditText editTextAge;
    TextView textViewPromptAge;
    View view;


    public PromptAgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_prompt_age, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userModel = bundle.getParcelable("userModel");
        }

        textViewPromptAge = (TextView)view.findViewById(R.id.textViewPromptAge);
        btnPromptAge = (Button)view.findViewById(R.id.btnPromptAge);
        editTextAge = (EditText)view.findViewById(R.id.editTextAge);
        textViewPromptAge.setText(textViewPromptAge.getText().toString().replace("{name}", userModel.name));

        return view;
    }

}
