package com.example.abdullahkucuk.fruittuin.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpeurtochtFragment extends Fragment {

    Button button;

    public SpeurtochtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_speurtocht, container, false);

        Button button = (Button) v.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                Fragment fragment = new PlattegrondFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_frame, fragment); // fragmen container id in first parameter is the  container(Main layout id) of Activity
                transaction.addToBackStack(null);  // this will manage backstack
                transaction.commit();
            }
        });
        return v;
    }

}
