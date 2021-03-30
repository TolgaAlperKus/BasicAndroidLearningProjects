package com.tolgaalperkus.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,
    imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText = findViewById(R.id.scoreTextView);
        timeText = findViewById(R.id.timeTextView);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3= findViewById(R.id.imageView3);
        imageView4= findViewById(R.id.imageView4);
        imageView5= findViewById(R.id.imageView5);
        imageView6= findViewById(R.id.imageView6);
        imageView7= findViewById(R.id.imageView7);
        imageView8= findViewById(R.id.imageView8);
        imageView9= findViewById(R.id.imageView9);
        score = 0;
        imageArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};


        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Kalan Zaman : "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Zaman Bitti!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Tekrar Dene!");
                alert.setMessage("Yeniden denemek ister misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Oyun Kapandı...",Toast.LENGTH_LONG).show();
                        finish();
                        System.exit(0);
                    }
                });
                alert.show();

            }
        }.start();

        hideImages();

    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,750);
            }
        };
        handler.post(runnable);

    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Puan : "+score);
    }
}