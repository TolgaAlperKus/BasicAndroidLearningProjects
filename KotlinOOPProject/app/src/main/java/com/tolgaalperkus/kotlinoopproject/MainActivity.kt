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
            //static polymorphism
        var math = Mathematics()
        println(math.sum())
        println(math.sum(3,5))
        println(math.sum(3,5,2))

            //dynamic polymorphism
        val animal = Animal()
        animal.sing()
        val barley = Dog()
        barley.test()
        barley.sing()

        //Abstract - Interface
        println(myUser.information())
        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital = false
        println(myPiano.roomName)
        myPiano.info()

        //Lambda Expressions
        fun printString (myString : String){
            println(myString)
        }
        printString("My test string")

        val testString = {myString : String -> println(myString)}
        testString("My Lambda String")

        val multiplyLambda = {a : Int , b : Int -> a*b}
        println(multiplyLambda(5,4))

        val multiplyLambda2 : (Int , Int) -> Int = {a,b -> a*b}
        println(multiplyLambda2(5,5))

        //Asynchronous
            //callback function, listener function, completion function
        fun downloadMusicians (url : String, completion : (Musician) -> Unit){
            //url -> Download
            //data
            val kirkHammet = Musician("Kirk","Guitar",60)
            completion(kirkHammet)
        }
        downloadMusicians("metallica.com",{musician ->
            println(musician.name)
        })

    }
}