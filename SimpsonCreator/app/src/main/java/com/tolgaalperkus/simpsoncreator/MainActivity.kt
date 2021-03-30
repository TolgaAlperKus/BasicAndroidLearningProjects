package com.tolgaalperkus.simpsoncreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun makeSimpson(view : View){

        name = nameText.text.toString()
        val age = ageText.text.toString().toIntOrNull()
        val job = jobText.text.toString()

        //resultText.text = "Name : $name, Age : $age , Job : $job"

        val simpson = Simpson(name,age,job)
        resultText.text = "Name : ${simpson.name}, Age : ${simpson.age} , Job : ${simpson.job}"


    }

    fun scopeExample(view : View){
        //Scope
        println(name)

    }
}