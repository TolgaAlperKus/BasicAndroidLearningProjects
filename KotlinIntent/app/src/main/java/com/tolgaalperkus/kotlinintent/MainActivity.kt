package com.tolgaalperkus.kotlinintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("on create called")
    }

    override fun onResume() {
        super.onResume()
        println("on resume called")
    }

    override fun onPause() {
        super.onPause()
        println("on pause called")
    }

    override fun onStop() {
        super.onStop()
        println("on stop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("on destroy called")
    }

    fun next (view: View){
        val intent = Intent(applicationContext,NextActivity::class.java)
        intent.putExtra("username",activity_main_username_edittext.text.toString())
        intent.putExtra("name",activity_main_name_edittext.text.toString())
        startActivity(intent)

    }


}