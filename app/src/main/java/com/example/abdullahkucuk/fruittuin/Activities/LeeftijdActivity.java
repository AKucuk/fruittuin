package com.example.abdullahkucuk.fruittuin.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.NetworkHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

public class LeeftijdActivity extends AppCompatActivity {
    LeeftijdActivity leeftijdActivity;
    EditText txtLeeftijd;
    Button btnLeeftijdVerder;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        leeftijdActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leeftijd);

        txtLeeftijd = (EditText) findViewById(R.id.txtLeeftijd);
        btnLeeftijdVerder = (Button) findViewById(R.id.btnLeeftijdVerder);
        userModel = getIntent().getParcelableExtra("user");

        btnLeeftijdVerder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getApplicationContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, "Geen internet connectie!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final String input = txtLeeftijd.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul je leeftijd in om verder te gaan...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                int leeftijd = Integer.parseInt(input);
                if(leeftijd <= 5) {
                    Toast.makeText(context, leeftijd + "?!?! Dat kan helemaal niet!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                userModel.leeftijd = leeftijd;

                Intent intent = new Intent(LeeftijdActivity.this, LocatieActivity.class);
                intent.putExtra("user", userModel);
                startActivity(intent);
            }
        });
    }
}
