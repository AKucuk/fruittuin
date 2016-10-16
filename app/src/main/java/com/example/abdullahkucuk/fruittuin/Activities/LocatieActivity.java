package com.example.abdullahkucuk.fruittuin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;

public class LocatieActivity extends AppCompatActivity {
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatie);

        userModel = getIntent().getParcelableExtra("user");

        TextView textView = (TextView) findViewById(R.id.textView8);
        textView.setText("Naam: " + userModel.name + ", Leeftijd: " + userModel.leeftijd);
    }
}
