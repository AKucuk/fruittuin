package com.example.abdullahkucuk.fruittuin.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdullahkucuk.fruittuin.Helpers.UrlHelper;
import com.example.abdullahkucuk.fruittuin.Models.UserModel;
import com.example.abdullahkucuk.fruittuin.R;
import com.example.abdullahkucuk.fruittuin.Services.WeatherHttpClient;


public class MainActivity extends AppCompatActivity {
    MainActivity mainActivity;
    Button button;
    TextView label;
    EditText editTextName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        label = (TextView)findViewById(R.id.textView);
        editTextName = (EditText)findViewById(R.id.editTextName);

        //new WeatherHttpClient(this).execute("Amsterdam");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                if(name.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Vul je naam in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                UserModel userModel = new UserModel();
                userModel.name = name;

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                intent.putExtra("user", userModel);
                startActivity(intent);

            }
        });
    }
}