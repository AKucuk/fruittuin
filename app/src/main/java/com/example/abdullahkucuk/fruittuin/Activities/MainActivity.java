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
import com.example.abdullahkucuk.fruittuin.R;


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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                if(name.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Vul je naam in, zodat we verder kunnen...", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);

                if(true)
                    return;
                try {

                    //new Downloader(mainActivity).execute("http://www.akucuk.com");

                }

                catch (Exception ex) {
                    label.setText(ex.getMessage());
                }
            }
        });
    }
}
/* TODO: Remove this
class Downloader extends AsyncTask<String, Void, String> {
    MainActivity mainActivity;
    public Downloader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
     protected String doInBackground(String... params) {
         try {
             return UrlHelper.getUrlContent(params[0]);
         } catch (Exception e) {
             e.printStackTrace();
         }
         return "";
     }

     @Override
     protected void onPostExecute(String result) {
        mainActivity.button.setText(result);
     }
 }
*/