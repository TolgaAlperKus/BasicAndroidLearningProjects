package com.tolgaalperkus.kotlinrunnables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var number = 0
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun startBtnClicked(view:View){
        number = 0
        runnable = object:Runnable{
            override fun run() {
                number = number + 1
                activity_main_countdown_textview.text = "Time : $number"

                handler.postDelayed(this,1000)

            }
        }
        handler.post(runnable)

    }
    fun stopBtnClicked(view:View){
        handler.removeCallbacks(runnable)
        number = 0
        activity_main_countdown_textview.text = "Time : 0"
    }
}