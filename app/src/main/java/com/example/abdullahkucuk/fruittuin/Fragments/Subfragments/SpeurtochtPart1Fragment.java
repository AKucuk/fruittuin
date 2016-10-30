package com.example.abdullahkucuk.fruittuin.Fragments.Subfragments;

import android.content.Intent;
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

import com.example.abdullahkucuk.fruittuin.Activities.IntroActivity;
import com.example.abdullahkucuk.fruittuin.Activities.MainActivity;
import com.example.abdullahkucuk.fruittuin.Fragments.PlattegrondFragment;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

import com.example.abdullahkucuk.fruittuin.Helpers.UrlHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.WeatherHttpClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeurtochtPart1Fragment extends Fragment {

    SpeurtochtPart1Fragment speurtochtPart1Fragment;

    Button button;
    TextView label;
    EditText editTextName;
    String name;

    public SpeurtochtPart1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        speurtochtPart1Fragment = this;
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speurtocht_part1, container, false);


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

                // go to plattegrond
                Fragment fragment = new SpeurtochtPart2Fragment();
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_frame_speurtocht, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });

        return view;
    }



}


