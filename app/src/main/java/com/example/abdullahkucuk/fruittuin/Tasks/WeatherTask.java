package com.example.abdullahkucuk.fruittuin.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Fragments.BetweenFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptTemperatureFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.PromptWindDirectionFragment;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.Weather;

/**
 * Created by abdullah.kucuk on 12-11-2016.
 */

public class WeatherTask extends AsyncTask<String, Void, Weather> {
    PromptTemperatureFragment promptTemperatureFragment;

    public WeatherTask(PromptTemperatureFragment promptTemperatureFragment) {
        this.promptTemperatureFragment = promptTemperatureFragment;
    }
    @Override
    protected Weather doInBackground(String... params) {
        String location = params[0];
        return new Weather(promptTemperatureFragment.getContext(), location);
    }

    @Override
    protected void onPostExecute(Weather weather) {
        Float f = weather.getCelcius();

        Context context = promptTemperatureFragment.getContext();
        int currentTemperatuur = Math.round(f);
        int guessedTemperatuur = Math.round(promptTemperatureFragment.guessedTemperatuur);

        if (guessedTemperatuur < currentTemperatuur) {
            Toast.makeText(context, guessedTemperatuur + "?? Het is toch niet zo koud...", Toast.LENGTH_LONG)
                    .show();
            return;
        } else if (guessedTemperatuur > currentTemperatuur) {
            Toast.makeText(context, guessedTemperatuur + "?? Het is toch niet zo warm?", Toast.LENGTH_LONG)
                    .show();
            return;
        } else {
            String betweenMessage = String.format("Goed! Om exact te zijn is het %s graden.\n\n", weather.getFormattedOutput(f));
            if(currentTemperatuur < 0) {
                betweenMessage += "Wauw, de temperatuur is onder het vriespunt! Dit wordt een lastige taak voor ons, maar niets is onmogelijk!";
            }
            else if(currentTemperatuur == 0) {
                betweenMessage += "Wauw het is precies 0 graden! Dat is het vriespunt. Wees voorzichtig!";
            }
            else if(currentTemperatuur < 11) {
                betweenMessage += "De temperatuur is een beetje fris, maar dat houdt ons niet tegen! Laten we aan het werk gaan.";
            }
            else if (currentTemperatuur < 16) {
                betweenMessage += "Met dit weer zou ik maar niet met korte mouwen lopen, want dan kan je snel ziek worden! Laten we aan het werk gaan.";
            }
            else if (currentTemperatuur < 20) {
                betweenMessage += "Dit is nou een lekker t-shirt weer. Weg met die lange mouwen! Laten we aan het werk gaan.";
            }
            else if(currentTemperatuur < 26) {
                betweenMessage += "Heerlijk weer! Onder de bijen noemen we dit honingweer. Vergeet je drinken niet mee te pakken want straks krijg je nog dorst!";
            }
            else {
                betweenMessage += String.format("Wauw, het is %d graden celcius... We gaan flink zweten zo te zien! Vergeet de polsbandjes niet te pakken.", currentTemperatuur);
            }

            BetweenFragment betweenFragment = new BetweenFragment();
            betweenFragment.setFragment(new PromptWindDirectionFragment());
            betweenFragment.setMessage(betweenMessage);

            FragmentHelper.addFragment(promptTemperatureFragment.getFragmentManager(), betweenFragment);
        }
    }
}
