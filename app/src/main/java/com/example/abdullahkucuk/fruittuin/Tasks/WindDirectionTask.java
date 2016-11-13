package com.example.abdullahkucuk.fruittuin.Tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptWindDirectionFragment;
import com.example.abdullahkucuk.fruittuin.Models.LuisEntityModel;
import com.example.abdullahkucuk.fruittuin.Models.WindDirectionModel;
import com.example.abdullahkucuk.fruittuin.Services.Luis;

import java.util.List;

/**
 * Created by abdullah.kucuk on 13-11-2016.
 */

public class WindDirectionTask extends AsyncTask<String, Void, Luis> {
    PromptWindDirectionFragment promptWindDirectionFragment;

    public WindDirectionTask(PromptWindDirectionFragment promptWindDirectionFragment) {
        this.promptWindDirectionFragment = promptWindDirectionFragment;
    }
    @Override
    protected Luis doInBackground(String... params) {
        String windDirection = params[0];

        return new Luis(promptWindDirectionFragment.getContext(), windDirection);
    }

    @Override
    protected void onPostExecute(Luis luis) {
        List<LuisEntityModel> entities = luis.getEntities();

        if(entities.size() == 0) {
            Toast.makeText(promptWindDirectionFragment.getContext(), "Ik heb je niet verstaan, typ je zin anders!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        LuisEntityModel entity = entities.get(0);

        WindDirectionModel windRichting = new WindDirectionModel(entity);
        if(windRichting.isWindDirection()) {
            Toast.makeText(promptWindDirectionFragment.getContext(), "Ik heb je niet verstaan, typ je zin anders!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        //windRichting.getWindDirection()
    }
}
