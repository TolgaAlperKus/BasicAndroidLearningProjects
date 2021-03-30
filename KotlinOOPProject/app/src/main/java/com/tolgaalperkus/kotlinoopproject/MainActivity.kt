package com.tolgaalperkus.kotlinoopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myUser = User("James" , 50)
        myUser.name = "Lars"
        myUser.age = 60
        println(myUser.name)
        println(myUser.age)

        var james = Musician("James" , "Guitar" , 55)

        println(james.age)

        james.returnBandName("dnemee")
        james.returnBandName("Atil")

        // Inheritance
        var lars = SuperMusician("Lars","Drums",65)
        println(lars.name)
        println(lars.returnBandName("Atil"))
        lars.sing()

        //polymorphism
        
    }
}