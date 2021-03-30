package com.tolgaalperkus.storingdatakotlin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences
    var ageFromPreferences : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //SharedPreferences Initialize
        sharedPreferences = this.getSharedPreferences(
            "com.tolgaalperkus.storingdatakotlin",
            MODE_PRIVATE
        )
        ageFromPreferences = sharedPreferences.getInt("age",-1)
        if(ageFromPreferences == -1){
            activity_main_age_textview.text = "Your Age:"
        }else
        {
            activity_main_age_textview.text = "Your Age: $ageFromPreferences"
        }
    }

    fun save (view : View){
        val myAge = activity_main_age_edittext.text.toString().toIntOrNull()
        if(myAge!= null){
            activity_main_age_textview.text = "Your Age: " +myAge
            sharedPreferences.edit().putInt("age" , myAge).apply()
        }
    }

    fun delete (view : View){
        ageFromPreferences = sharedPreferences.getInt("age",-1)
        if(ageFromPreferences != -1){
            sharedPreferences.edit().remove("age").apply()
            activity_main_age_textview.text = "Your Age:"
        }
    }
}