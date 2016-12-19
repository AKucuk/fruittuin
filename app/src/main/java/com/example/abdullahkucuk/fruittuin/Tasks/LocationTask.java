package com.example.abdullahkucuk.fruittuin.Tasks;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Fragments.BetweenFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.AcclimatizeFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptLocationFragment;
import com.example.abdullahkucuk.fruittuin.Global.Memory;
import com.example.abdullahkucuk.fruittuin.Global.Session;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.GoogleDistanceMatrix;

/**
 * Created by abdullah.kucuk on 6-11-2016.
 */

public class LocationTask extends AsyncTask<String, Void, GoogleDistanceMatrix> {
    PromptLocationFragment promptLocationFragment;

    public LocationTask(PromptLocationFragment promptLocationFragment){
        this.promptLocationFragment = promptLocationFragment;
    }

    @Override
    protected GoogleDistanceMatrix doInBackground(String... params) {
        String destination = params[0];
        return new GoogleDistanceMatrix(destination);
    }

    @Override
    protected void onPostExecute(GoogleDistanceMatrix googleDistanceMatrix) {
        if(googleDistanceMatrix.getStatus() == GoogleDistanceMatrix.Status.NOT_FOUND) {
            Toast.makeText(promptLocationFragment.getContext(),
                    String.format("Ik ken [%s] helemaal niet... Heb je het misschien fout getypt?", googleDistanceMatrix.getDestination()),
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        String reply;
        if(googleDistanceMatrix.getDestination().equalsIgnoreCase("Amsterdam")){
            reply = "Hah! Dan woon jij niet zo heeeel ver van hier!";
        }
        else {
            reply = String.format("Ah! Sisi de Sprinkhaan woont in %s! Dat is %s van hier vandaan en wel %s met de auto!"
            , googleDistanceMatrix.getDestination()
            , googleDistanceMatrix.getDistanceText()
            , googleDistanceMatrix.getDurationText());
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable("userModel", promptLocationFragment.getUserModel());

        Session session = Memory.getInstance();
        session.setLocation(googleDistanceMatrix.getDestination());


        Fragment fragment = new AcclimatizeFragment();
        fragment.setArguments(bundle);

        String page6Text= promptLocationFragment.getResources().getString(R.string.page_6_text);

        BetweenFragment betweenFragment2 = new BetweenFragment();
        betweenFragment2.setFragment(fragment);
        betweenFragment2.setMessage(page6Text);

        BetweenFragment betweenFragment1 = new BetweenFragment();
        betweenFragment1.setMessage(reply);
        betweenFragment1.setFragment(betweenFragment2);

        FragmentHelper.addFragment(promptLocationFragment.getFragmentManager(), betweenFragment1);
    }
}
