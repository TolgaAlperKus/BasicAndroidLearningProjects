package com.tolgaalperkus.catchthekennykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Image Array
        imageArray.add(activity_main_kenny_imageview1)
        imageArray.add(activity_main_kenny_imageview2)
        imageArray.add(activity_main_kenny_imageview3)
        imageArray.add(activity_main_kenny_imageview4)
        imageArray.add(activity_main_kenny_imageview5)
        imageArray.add(activity_main_kenny_imageview6)
        imageArray.add(activity_main_kenny_imageview7)
        imageArray.add(activity_main_kenny_imageview8)
        imageArray.add(activity_main_kenny_imageview9)
        hideImages()

        //countdowntimer
        object: CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                activity_main_timer_textview.text = "Time :"+millisUntilFinished/1000
            }

            override fun onFinish() {
                activity_main_timer_textview.text = "Time Finished"

                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                    
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Try Again ?")
                alert.setPositiveButton("Yes"){listener, which ->
                    //restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {listener, which ->
                    //game over
                    Toast.makeText(this@MainActivity, "Game Over!", Toast.LENGTH_LONG).show()
                }
                alert.show()
            }

        }.start()
    }
    fun increaseScore (view: View){
        score++
        activity_main_score_textview.text = "Score : $score"


    }

    fun hideImages () {
        runnable = object : Runnable{
            override fun run() {

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,600)
            }

        }
        handler.post(runnable)
        
    }
}