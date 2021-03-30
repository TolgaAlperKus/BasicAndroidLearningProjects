package com.tolgaalperkus.parselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        //Parse tüm veriyi çekme ve filtreleme
        /*
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e!=null)e.printStackTrace();
                else{
                    if(objects.size()>0){
                        for (ParseObject object : objects){
                            String objectName = object.getString("name");
                            int objectCalorie = object.getInt("calories");

                            System.out.println("object name > "+objectName+" objectCal > "+objectCalorie);
                        }
                    }
                }
            }
        });*/
        //Parse veri çekme
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fruits");
        query.getInBackground("LAcPTOILI1", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e!=null)e.printStackTrace();
                else{
                    String objectName = object.getString("name");
                    int objectCal = object.getInt("calories");

                    System.out.println("object name ->"+objectName +" Object Cal ->"+ objectCal);

                }
            }
        });*/
        //Parse veri ekleme
        /*
        ParseObject object = new ParseObject("Fruits");
        object.put("name","banana");
        object.put("calories",150);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Object saved", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        //Parse kullanıcı ekleme kayıt
        /*
        ParseUser user = new ParseUser();
        user.setUsername("James");
        user.setPassword("123456");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!= null)e.printStackTrace();
                else{
                    Toast.makeText(MainActivity.this, "User signed up", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        //Parse kullanıcı girişi
        ParseUser.logInInBackground("James", "123456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "hoşgeldin"+user.getUsername(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}