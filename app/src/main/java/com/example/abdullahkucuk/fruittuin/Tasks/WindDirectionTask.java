package com.example.abdullahkucuk.fruittuin.Tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Enumerations.WindDirection;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.BetweenAlternativeFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PictureFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.ClayFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.GroundtypeFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.ProefPaardenbloemFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptWindDirectionFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptRatingFragment;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.Weather;

import java.util.Arrays;

/**
 * Created by abdullah.kucuk on 13-11-2016.
 */

public class WindDirectionTask extends AsyncTask<WindDirection, Void, WindDirection> {
    PromptWindDirectionFragment promptWindDirectionFragment;
    WindDirection enteredWindDirection;

    public WindDirectionTask(PromptWindDirectionFragment promptWindDirectionFragment) {
        this.promptWindDirectionFragment = promptWindDirectionFragment;
    }
    @Override
    protected WindDirection doInBackground(WindDirection... params) {
        enteredWindDirection = params[0];

        return new Weather(promptWindDirectionFragment.getContext(), "Amsterdam").getRoughWindDirection();
        //return new Luis(promptWindDirectionFragment.getContext(), windDirection);
    }

    @Override
    protected void onPostExecute(WindDirection windDirection) {
        //List<LuisEntityModel> entities = luis.getEntities();
        if(enteredWindDirection == windDirection) {
            showImageQuiz();
        }
        else {
            Toast.makeText(promptWindDirectionFragment.getContext(), "De wind komt van een andere kant, probeer opnieuw!", Toast.LENGTH_LONG)
                    .show();
        }


        /*LuisEntityModel entity = entities.get(0);

        WindDirectionModel windRichting = new WindDirectionModel(entity);
        if(!windRichting.isWindDirection()) {
            Toast.makeText(promptWindDirectionFragment.getContext(), "Ik heb je niet verstaan, typ je zin anders!", Toast.LENGTH_LONG)
                    .show();
            return;
        }*/

        //// TODO: 21-12-2016  Check wind direction and give feedback
        //windRichting.getWindDirection()


    }

    private void showImageQuiz() {

        PromptRatingFragment promptRatingFragment = new PromptRatingFragment();

        //successorChickenFragment:
        BetweenAlternativeFragment finishFragment = new BetweenAlternativeFragment();
        finishFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.finish_fragment));
        finishFragment.setFragment(promptRatingFragment);

        //successorChickenFragment:
        BetweenAlternativeFragment successorChickenFragment = new BetweenAlternativeFragment();
        successorChickenFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_chicken_explanation));
        successorChickenFragment.setFragment(finishFragment);

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
        successorCherryFragment.setTextPartOne(promptWindDirectionFragment.getResources().getString(R.string.picture_fragment_text_cherry_explanation));
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

        FragmentHelper.addFragment(promptWindDirectionFragment.getFragmentManager(), pictureFragment);
    }
}
