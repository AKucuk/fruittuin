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

import com.example.abdullahkucuk.fruittuin.Fragments.BetweenFragment;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptLocationFragment extends Fragment {
    View view;
    Button btnLocatie;
    EditText txtLocatie;
    UserModel userModel;

    public PromptLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userModel = bundle.getParcelable("userModel");
        }

        view = inflater.inflate(R.layout.fragment_prompt_location, container, false);

        btnLocatie = (Button) view.findViewById(R.id.btnLocatie);
        txtLocatie = (EditText) view.findViewById(R.id.txtLocatie);

        btnLocatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String locatie = txtLocatie.getText().toString();
                if(locatie.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Vul je locatie in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String messageLocationReply = "Oooh daar heb ik weleens van gehoord. Welkom in Amsterdam.";

                Bundle bundle = new Bundle();
                bundle.putParcelable("userModel", userModel);

                Fragment fragment = new AcclimatizeFragment();
                fragment.setArguments(bundle);

                String page6Text= getResources().getString(R.string.page_6_text);

                BetweenFragment betweenFragment2 = new BetweenFragment();
                betweenFragment2.setFragment(fragment);
                betweenFragment2.setMessage(page6Text);

                BetweenFragment betweenFragment1 = new BetweenFragment();
                betweenFragment1.setMessage(messageLocationReply);
                betweenFragment1.setFragment(betweenFragment2);

                FragmentHelper.addFragment(getFragmentManager(), betweenFragment1);

                
            }
        });

        return view;
    }

}
