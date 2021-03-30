package com.tolgaalperkus.classesandfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()

        //Unit - Void değer döndürmeyen method
        mySum(5,3)
        //return
        val result = myMuliply(10,20)
        textView.text="Result : $result"

        //button.setOnClickListener { println("clicked") }
        //Class
        val homer = Simpson("Homer Simpson",50,"Nuclear")
        //Object & Instance
        /*
        homer.age = 60
        homer.name = "Homer"
        homer.job = "Nuc"

         */
        println(homer.age)
        //homer.hairColor = "Yellow"
        // println(homer.hairColor)
        homer.changeHairColor();

        //Nullability
            //Nullable (?) && Non-null
        var myString : String? = null // ? means myString could be null
        var myAge : Int? = null
        //println(myAge!! *10) //!! means myAge processed like initialized

        //1) Null Safety
        if(myAge !=null){
            println(myAge)
        }else{
            println("myAge null")
        }

        //2) Safe Call

        println(myAge?.compareTo(2))

        //3) Elvis

        val myResult = myAge?.compareTo(2) ?:-100
        println(myResult)
        
        



    }

    fun test(){
        println("test function")
    }


    //input - return
    fun mySum(a:Int , b:Int){
        textView.text = "Result = ${a+b}"
    }

    fun myMuliply(x:Int , y :Int):Int{
        return x*y
    }

    fun helloKotlin(view : View){
        textView.text = "Hello Kotlin"
    }

}