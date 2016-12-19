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

import com.example.abdullahkucuk.fruittuin.Global.Memory;
import com.example.abdullahkucuk.fruittuin.Global.Session;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

import java.util.Date;

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

        KeyboardHelper.hideKeyboard(getActivity());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                name = editTextName.getText().toString();
                if(name.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Vul je naam in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Session session = Memory.getInstance();
                session.setName(name);


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


