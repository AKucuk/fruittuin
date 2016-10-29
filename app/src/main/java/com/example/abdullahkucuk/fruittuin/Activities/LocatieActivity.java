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
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

public class LocatieActivity extends AppCompatActivity {
    UserModel userModel;
    Button btnLocatie;
    EditText txtLocatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatie);

        userModel = getIntent().getParcelableExtra("user");
        btnLocatie = (Button) findViewById(R.id.btnLocatie);
        txtLocatie = (EditText) findViewById(R.id.txtLocatie);

        btnLocatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = getApplicationContext();
                if(!NetworkHelper.isOnline(context)) {
                    Toast.makeText(context, "Geen internet connectie!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                final String input = txtLocatie.getText().toString();
                if(input.isEmpty()) {
                    Toast.makeText(context, "Vul je woonplaats in zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

            }
        });
    }
}
