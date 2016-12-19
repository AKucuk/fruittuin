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

import com.example.abdullahkucuk.fruittuin.Fragments.BetweenFragment;
import com.example.abdullahkucuk.fruittuin.Global.Memory;
import com.example.abdullahkucuk.fruittuin.Global.Session;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
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
        btnPromptAge = (Button) view.findViewById(R.id.btnTemperatuur);
        editTextAge = (EditText) view.findViewById(R.id.txtTemperatuur);
        textViewPromptAge.setText(textViewPromptAge.getText().toString().replace("{name}", userModel.name));

        KeyboardHelper.hideKeyboard(getActivity());

        btnPromptAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String input = editTextAge.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul je leeftijd in om verder te gaan...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                int leeftijd;
                try {
                    leeftijd = Integer.parseInt(input);
                    if (leeftijd <= 5) {
                        Toast.makeText(context, leeftijd + "?!?! Dat kan helemaal niet!", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    else if(leeftijd > 80) {
                        Toast.makeText(context, leeftijd + "??? Dat kan niet ouwe!", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                }
                catch (Exception exception) {
                    Toast.makeText(context, "Vul een getal in!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                userModel.leeftijd = leeftijd;

                Bundle bundle = new Bundle();
                bundle.putParcelable("userModel", userModel);

                Session session = Memory.getInstance();
                session.setAge(leeftijd);


                Fragment fragment = new PromptLocationFragment();
                fragment.setArguments(bundle);

                BetweenFragment betweenFragment = new BetweenFragment();
                betweenFragment.setFragment(fragment);
                betweenFragment.setMessage(getResources().getString(R.string.page_4_text));

                FragmentHelper.addFragment(getFragmentManager(), betweenFragment);
            }
        });

        return view;
    }

}
