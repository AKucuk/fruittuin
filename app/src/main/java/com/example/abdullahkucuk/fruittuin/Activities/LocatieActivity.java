package com.example.abdullahkucuk.fruittuin.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                
            }
        });
    }
}
