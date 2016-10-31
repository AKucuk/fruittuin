package com.example.abdullahkucuk.fruittuin.Fragments.Subfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptNameFragment extends Fragment {
    PromptNameFragment promptNameFragment;

    Button button;
    TextView label;
    EditText editTextName;
    String name;

    public PromptNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        promptNameFragment = this;
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prompt_name, container, false);


        button = (Button)view.findViewById(R.id.button);
        label = (TextView)view.findViewById(R.id.textView);
        editTextName = (EditText)view.findViewById(R.id.editTextName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Vul je naam in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                UserModel userModel = new UserModel();
                userModel.name = name;

                Bundle bundle = new Bundle();
                bundle.putParcelable("userModel", userModel);

                Fragment fragment = new PromptAgeFragment();
                fragment.setArguments(bundle);

                FragmentHelper.addFragment(getFragmentManager(), fragment);
            }
        });

        return view;
    }



}


