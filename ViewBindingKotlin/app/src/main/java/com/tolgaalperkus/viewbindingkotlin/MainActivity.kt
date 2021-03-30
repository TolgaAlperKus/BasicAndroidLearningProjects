package com.tolgaalperkus.viewbindingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.tolgaalperkus.viewbindingkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var textView: TextView
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //textView = findViewById<TextView>(R.id.textView)
    }

    fun ismiDegistir(view : View){
        println("degistir tiklandi")

       // textView.text = "Merhaba kotlin"
        binding.textView.text = "Merhaba Kotlin"
    }
}