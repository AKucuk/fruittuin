package com.example.abdullahkucuk.fruittuin.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.R;

public class IntroActivity extends AppCompatActivity {
    TextView textViewWelkom;
    String name;
    Button buttonVolgende;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Context context = getApplicationContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, "Geen internet connectie!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String input = editText.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul iets in!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Toast.makeText(context, "Hier komt luis herkenner...", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
