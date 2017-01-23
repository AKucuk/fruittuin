package com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.KeyboardHelper;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.R;

import java.util.Date;

import static android.R.attr.name;
import static com.example.abdullahkucuk.fruittuin.Activities.MainActivity.getPostRef;
import static com.example.abdullahkucuk.fruittuin.Activities.MainActivity.mFirebaseAnalytics;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromptRatingFragment extends Fragment {


    Button btnVolgende;
    TextView txtRating;
    ImageView imgRating;
    RatingBar ratingBar;
    EditText editTextFeedback;
    String feedback;
    Float rating;

    public PromptRatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);


        txtRating = (TextView) view.findViewById(R.id.txtRating);
        txtRating.setText(R.string.rating_fragment);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
        editTextFeedback = (EditText) view.findViewById(R.id.editTextFeedback);
        btnVolgende = (Button) view.findViewById(R.id.btnVersturen);

        KeyboardHelper.hideKeyboard(getActivity());

        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                if (!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                rating = ratingBar.getRating();

                if (rating == 0.0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Geef aub een rating", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                feedback = editTextFeedback.getText().toString();
                if (feedback.isEmpty()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Geef aub een korte feedback", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                getPostRef().child("date_end").setValue(new Date().toString());
                getPostRef().child("rating").setValue(rating);
                getPostRef().child("feedback").setValue(feedback);
                mFirebaseAnalytics.setUserProperty("end_date", new Date().toString());

                Fragment fragment = new TheEndFragment();
                FragmentHelper.addFragment(getFragmentManager(), fragment);
            }
        });

        return view;
    }

}
