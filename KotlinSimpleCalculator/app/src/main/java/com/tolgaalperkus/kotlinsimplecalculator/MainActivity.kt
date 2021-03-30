package com.tolgaalperkus.kotlinsimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var firstNumber : Int? = null
    var secondNumber : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mySum (view : View) {
        firstNumber = editText.text.toString().toIntOrNull()
        secondNumber = editText2.text.toString().toIntOrNull()
        if(firstNumber != null && secondNumber != null){
            resultText.setText("Result :" + (firstNumber!!+secondNumber!!))
        }
        else{
            resultText.setText("Do not leave empty")
        }
    }

    fun mySub (view:View){
        firstNumber = editText.text.toString().toIntOrNull()
        secondNumber = editText2.text.toString().toIntOrNull()
        if(firstNumber != null && secondNumber != null){
            resultText.setText("Result :" + (firstNumber!!-secondNumber!!))
        }
        else{
            resultText.setText("Do not leave empty")
        }

    }

    fun myMultiply(view:View){
        firstNumber = editText.text.toString().toIntOrNull()
        secondNumber = editText2.text.toString().toIntOrNull()
        if(firstNumber != null && secondNumber != null){
            resultText.setText("Result :" + (firstNumber!!*secondNumber!!))
        }
        else{
            resultText.setText("Do not leave empty")
        }

    }

    fun myDiv(view:View){
        firstNumber = editText.text.toString().toIntOrNull()
        secondNumber = editText2.text.toString().toIntOrNull()
        if(firstNumber != null && secondNumber != null){
            resultText.setText("Result :" + (firstNumber!!/secondNumber!!))
        }
        else{
            resultText.setText("Do not leave empty")
        }

    }
}