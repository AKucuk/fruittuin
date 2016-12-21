package com.example.abdullahkucuk.fruittuin.Tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;
import com.example.abdullahkucuk.fruittuin.Fragments.BetweenAlternativeFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.PictureFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.ClayFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.GroundtypeFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.ProefPaardenbloemFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptWindDirectionFragment;
import com.example.abdullahkucuk.fruittuin.Models.LuisEntityModel;
import com.example.abdullahkucuk.fruittuin.Models.WindDirectionModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.Luis;

import java.util.Arrays;
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
        if(!windRichting.isWindDirection()) {
            Toast.makeText(promptWindDirectionFragment.getContext(), "Ik heb je niet verstaan, typ je zin anders!", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        //// TODO: 21-12-2016  Check wind direction and give feedback
        //windRichting.getWindDirection()


        showImageQuiz();
    }

    private void showImageQuiz() {
        //successorChickenFragment:
        BetweenAlternativeFragment successorChickenFragment = new BetweenAlternativeFragment();
        successorChickenFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_chicken_explanation));
        //successorChickenFragment.setFragment(chickenFragment);

        //chickenFragment
        PictureFragment chickenFragment = new PictureFragment();
        chickenFragment.setFragment(successorChickenFragment);
        chickenFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_chicken));
        chickenFragment.setToFindDutch(Arrays.asList("kip", "vogel"));
        chickenFragment.setToFindEnglish(Arrays.asList("chicken", "bird"));
        chickenFragment.setNumberOfTries(4);

        //successorBlackBerryFragment:
        BetweenAlternativeFragment successorBlackBerryFragment = new BetweenAlternativeFragment();
        successorBlackBerryFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_blackberry_explanation));
        successorBlackBerryFragment.setFragment(chickenFragment);

        //blackBerryFragment
        PictureFragment blackBerryFragment = new PictureFragment();
        blackBerryFragment.setFragment(successorBlackBerryFragment);
        blackBerryFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_blackberry));
        blackBerryFragment.setToFindDutch(Arrays.asList("braam", "fruit"));
        blackBerryFragment.setToFindEnglish(Arrays.asList("blackberry", "fruit"));
        blackBerryFragment.setNumberOfTries(4);

        //successorCherryFragment:
        BetweenAlternativeFragment successorCherryFragment = new BetweenAlternativeFragment();
        successorCherryFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_raspberry_explanation));
        successorCherryFragment.setFragment(blackBerryFragment);

        //raspBerryFragment
        PictureFragment cherryPictureFragment = new PictureFragment();
        cherryPictureFragment.setFragment(successorCherryFragment);
        cherryPictureFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_cherry));
        cherryPictureFragment.setToFindDutch(Arrays.asList("kers", "fruit"));
        cherryPictureFragment.setToFindEnglish(Arrays.asList("cherry", "fruit"));
        cherryPictureFragment.setNumberOfTries(4);

        //successorRaspBerryFragment:
        BetweenAlternativeFragment successorRaspBerryFragment = new BetweenAlternativeFragment();
        successorRaspBerryFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_raspberry_explanation));
        successorRaspBerryFragment.setFragment(cherryPictureFragment);

        //raspBerryFragment
        PictureFragment raspberryPictureFragment = new PictureFragment();
        raspberryPictureFragment.setFragment(successorRaspBerryFragment);
        raspberryPictureFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_raspberry));
        raspberryPictureFragment.setToFindDutch(Arrays.asList("framboos", "fruit"));
        raspberryPictureFragment.setToFindEnglish(Arrays.asList("raspberry", "fruit"));
        raspberryPictureFragment.setNumberOfTries(4);

        //clayFragment
        ClayFragment clayFragment = new ClayFragment();
        clayFragment.setFragment(raspberryPictureFragment);

        //groundtypeFragment
        GroundtypeFragment groundtypeFragment = new GroundtypeFragment();
        groundtypeFragment.setFragment(clayFragment);

        //proefPaardenbloemFragment
        ProefPaardenbloemFragment tasteDandelionFragment = new ProefPaardenbloemFragment();
        tasteDandelionFragment.setFragment(groundtypeFragment);

        //successorDandelion:
        BetweenAlternativeFragment successorDandelionFragment = new BetweenAlternativeFragment();
        successorDandelionFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_paardenbloem_eetbaar));
        successorDandelionFragment.setFragment(tasteDandelionFragment);

        //dandelionPictureFragment
        PictureFragment pictureFragment = new PictureFragment();
        pictureFragment.setFragment(successorDandelionFragment);
        pictureFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_paardenbloem));
        pictureFragment.setToFindDutch(Arrays.asList("paardenbloem", "bloem"));
        pictureFragment.setToFindEnglish(Arrays.asList("dandelion", "flower"));
        pictureFragment.setNumberOfTries(4);
        promptWindDirectionFragment.getFragmentManager().beginTransaction().replace(R.id.fragment_frame, pictureFragment).commit();

        //apple
//        PictureFragment pictureFragment = new PictureFragment();
//        pictureFragment.setFragment(fragment);
//        pictureFragment.setMessage(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_apple));
//        pictureFragment.setToFindDutch(Arrays.asList("appel", "fruit"));
//        pictureFragment.setToFindEnglish(Arrays.asList("apple", "fruit"));
//        pictureFragment.setNumberOfTries(4);
//        ft.beginTransaction().replace(R.id.fragment_frame, pictureFragment).commit();

    }
}
