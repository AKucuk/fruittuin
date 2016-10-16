package com.example.abdullahkucuk.fruittuin.Activities;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Constants.Intents;
import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.IntentModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.Luis;

public class IntroActivity extends AppCompatActivity {
    IntroActivity introActivity;
    TextView textViewWelkom;
    String name;
    Button buttonVolgende;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        introActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        textViewWelkom = (TextView)findViewById(R.id.textViewWelkom);
        buttonVolgende = (Button)findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editText);

        name = getIntent().getStringExtra("name");
        textViewWelkom.setText(textViewWelkom.getText().toString().replace("{name}", name));

        buttonVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getApplicationContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, "Geen internet connectie!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final String input = editText.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul iets in!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                new IntroActivityTask(introActivity).execute(input);
            }
        });
    }
}
class IntroActivityTask extends AsyncTask<String, Void, IntentModel> {
    IntroActivity introActivity;
    public IntroActivityTask(IntroActivity introActivity) {
        this.introActivity = introActivity;
    }

    @Override
    protected IntentModel doInBackground(String... params) {
        try {
            Luis luis = new Luis(introActivity.getApplicationContext(), params[0]);
            return luis.getBestIntent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(IntentModel result) {
        if(result != null){
            switch(result.getIntent()) {
                case Intents.YES_INTENT:
                    Toast.makeText(introActivity.getApplicationContext(), "TODO: VOLGEND SCHERM AANROEPEN",
                            Toast.LENGTH_LONG).show();
                    break;
                case Intents.NO_INTENT:
                    Toast.makeText(introActivity.getApplicationContext(), "Jammer! Dan kunnen we helaas niet samen op avontuur",
                            Toast.LENGTH_LONG).show();
                    break;
                case Intents.DONT_KNOW_INTENT:
                    Toast.makeText(introActivity.getApplicationContext(), "Kom op! Wil je me echt niet helpen?",
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(introActivity.getApplicationContext(), "Ik heb je helaas niet verstaan. Formuleer je zin iets anders.",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }

    }
}