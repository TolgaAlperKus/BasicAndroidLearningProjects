package com.tolgaalperkus.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    SharedPreferences sharedPreferences;
    int userAge = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextAge);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.tolgaalperkus.storingdata", Context.MODE_PRIVATE);
        userAge = sharedPreferences.getInt("storedAge",0);
        if (userAge != 0){
            textView.setText("Yaşınız : "+userAge);
        }
    }

    public void save (View view){
        if(editText.getText().toString().matches(""))
        {
            Toast.makeText(getApplicationContext(),"Bir değer giriniz.",Toast.LENGTH_LONG).show();
        }else{
            userAge = Integer.parseInt(editText.getText().toString());
            textView.setText(String.format("Yaşınız : %s", userAge));
            sharedPreferences.edit().putInt("storedAge",userAge).apply();
        }

    }

    public void delete(View view){
        int storedData = sharedPreferences.getInt("storedAge",0);
        if(storedData!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Yaşınız : ");
        }
    }

}