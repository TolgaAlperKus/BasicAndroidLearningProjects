package com.tolgaalperkus.instagramcloneparse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    Button signInBtn, signUpBtn;
    EditText usernameText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signInBtn = findViewById(R.id.sing_up_activity_sign_in_button);
        signUpBtn = findViewById(R.id.sign_up_activity_sign_up_button);
        usernameText = findViewById(R.id.sign_up_activity_name_text);
        passwordText = findViewById(R.id.sign_up_activity_password_text);
        showui(true);
        ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser!=null){
            Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void signUp(View view){
        showui(false);
        ParseUser user = new ParseUser();
        user.setUsername(usernameText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    showui(true);
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public void signIn(View view){
        showui(false);
        ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    showui(true);
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Welcome "+user.getUsername(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    public void showui(boolean show){
        if(show){
            findViewById(R.id.upload_activity_loading_panel).setVisibility(View.GONE);
            signInBtn.setVisibility(View.VISIBLE);
            signUpBtn.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);
            passwordText.setVisibility(View.VISIBLE);
        }
        else{
            signInBtn.setVisibility(View.GONE);
            signUpBtn.setVisibility(View.GONE);
            passwordText.setVisibility(View.GONE);
            usernameText.setVisibility(View.GONE);
            findViewById(R.id.upload_activity_loading_panel).setVisibility(View.VISIBLE);
        }
    }
}