package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Constants.Intents;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.IntentModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.GoogleDistanceMatrix;
import com.example.abdullahkucuk.fruittuin.Services.Luis;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    Button buttonVolgende;
    View view;
    EditText textStartSpeurtocht;
    StartFragment startFragment;

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        startFragment = this;
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.fragment_start, container, false);

        textStartSpeurtocht = (EditText) view.findViewById(R.id.textStartSpeurtocht);
        buttonVolgende = (Button) view.findViewById(R.id.buttonVolgende);

        buttonVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.addFragment(getFragmentManager(), new PromptNameFragment());
            }
        });

        buttonVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                if (!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final String input = textStartSpeurtocht.getText().toString();
                if (input.isEmpty()) {
                    Toast.makeText(context, "Vul iets in!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }


                new StartFragmentTask(startFragment).execute(input);
            }
        });

        return view;
    }


    class StartFragmentTask extends AsyncTask<String, Void, IntentModel> {
        StartFragment startFragment;

        public StartFragmentTask(StartFragment startFragment) {
            this.startFragment = startFragment;
        }

        @Override
        protected IntentModel doInBackground(String... params) {
            try {
                Luis luis = new Luis(startFragment.getActivity().getApplicationContext(), params[0]);
                return luis.getBestIntent();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(IntentModel result) {
            if (result != null) {
                switch (result.getIntent()) {
                    case Intents.YES_INTENT:
                        Fragment fragment = new PromptNameFragment();
                        FragmentHelper.addFragment(getFragmentManager(), fragment);
                        break;
                    case Intents.NO_INTENT:
                        Toast.makeText(startFragment.getActivity().getApplicationContext(), "Jammer! Dan kunnen we helaas niet samen op avontuur",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Intents.DONT_KNOW_INTENT:
                        Toast.makeText(startFragment.getActivity().getApplicationContext(), "Kom op! Wil je me echt niet helpen?",
                                Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(startFragment.getActivity().getApplicationContext(), "Ik heb je helaas niet verstaan. Typ je zin iets anders.",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }

        }
    }
}