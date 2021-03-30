package com.tolgaalperkus.kotlinlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables
        var x = 5
        var y = 4

        println(x*y)

        var age = 35
        val result = age / 7 * 5
        println(result)
        //Defining
        val myInteger : Int

        //initialize
        myInteger = 10

        val a : Int = 23
        println(a/2)

        //Long
        var myLong : Long = 100


        //Double & Float

        val pi = 3.14
        println(pi*2)

        val myFloat = 3.14f
        println(myFloat*2)

        var myAge : Double
        myAge = 23.0
        println(myAge/2)

        //String

        val myString = "tolga Alper Kuş"
        val name = "James"
        val surname = "Hetfield"
        val fullname = name +" "+ surname
        println (fullname)

        val larsName : String = "Lars"

        println(myString.capitalize())

        //Boolean
        var myBoolean : Boolean = true
        myBoolean = false
        var isAlive = true
        // <
        // >
        // <=
        // >=
        // ==
        // !=
        // && -> And
        // || -> Or

        println(3<5)
        println(6<3)
        println(3==3)
        println(4!=5)

        //Conversion

        var myNumber = 5
        var myLongNumber = myNumber.toLong()
        println(myLongNumber)

        var input = "10"
        var inputInteger = input.toInt()
        println(inputInteger * 2)

        //Collections

            //Array

        val myArray = arrayOf("James","Kirk","Rob","Lars")

        //index
        println(myArray[0])
        myArray[0] = "James Hetfield"
        println(myArray[0])
        println(myArray)

        myArray.set(1,"Kirk Hammet")
        println(myArray[1])

        //myArray[4] = "Tolga Alper Kuş"
        //println(myArray[4]) // doesnt work

        val numberArray = arrayOf(1,2,3,4,5)
        println(numberArray[3])
        //println(numberArray[5]) app crash

        val myNewArray = doubleArrayOf(1.0,2.0,3.0)

        val mixedArray = arrayOf("Atil",5)
        println(mixedArray[0])
        println(mixedArray[1])

        // List - ArrayList

        val myMusicians = arrayListOf<String>("James","Kirk")
        myMusicians.add("Lars")
        println(myMusicians[2])
        myMusicians.add(0,"Rob")
        println(myMusicians[0])

        val myArrayList = ArrayList<Int>()
        myArrayList.add(1)
        myArrayList.add(200)

        val myMixedArrayList = ArrayList<Any>()
        myMixedArrayList.add("Atil")
        myMixedArrayList.add(12.25)
        myMixedArrayList.add(true)

        println(myMixedArrayList[0])
        println(myMixedArrayList[1])
        println(myMixedArrayList[2])

        //Set

        val myExampleArray = arrayOf(1,1,2,3,4)
        println(" element 1: ${myExampleArray[0]}")
        println(" element 2: ${myExampleArray[1]}")

        val mySet = setOf<Int>(1,1,2,3)
        println(mySet.size)

        //For Each
        mySet.forEach { println(it) }

        val myStringSet = HashSet<String>()
        myStringSet.add("Tolga")
        myStringSet.add("Tolga")
        println(myStringSet.size)

        //Map - HashMap

            //key value pairing

        val fruitArray = arrayOf("Apple","Banana")
        val caloriesArray = arrayOf(100,150)

        println("${fruitArray[0]} : ${caloriesArray[0]}")

        val fruitCallorieMap = hashMapOf<String,Int>()
        fruitCallorieMap.put("Apple",100)
        fruitCallorieMap.put("Banana",150)
        println(fruitCallorieMap["Apple"])

        var myHashMap = HashMap<String,String>()
        val myNewMap = hashMapOf<String,Int>("A" to 1 , "B" to 2 , "C" to 3 )
        println(myNewMap["C"])

        //Operator
        var m = 5
        println(m)
        m = m + 1
        println(m)
        m++
        println(m)
        m--
        println(m)

        var n = 7
        println(n>m)

        println(n>m && 2>1)
        println(n>m && 1>2)

        println(n>m || 1>2)

        println(10+2)
        println(10-2)
        println(10*2)
        println(10/2)

        //Remainder
        println(10%4)

        //If Control

        val myNumberAge = 32
        if(myNumberAge<30){
            println("<30")
        }
        else if (myNumberAge>=30 && myNumberAge< 40){
            println(">=30 && 40")
        }
        else if(myNumberAge>=40&& myNumberAge <50){
            println("40-50")
        }
        else{
            println(">=50")
        }

        //Switch

        val day = 3
        var dayString = ""

        /*
        if(day == 1){
            dayString = "Monday"
        }else if(day == 2){
            dayString = "Tuesday"
        }else if(day == 3){
            dayString = "Wednesday"
        }

        println(dayString)

         */

        when(day){
            1 -> dayString = "Monday"
            2 -> dayString = "Tuesday"
            3 -> dayString = "Wednesday"
            else -> dayString = ""
        }
        println(dayString)

        //Loops
        //For Loop
        val myArrayOfNumbers = arrayOf(12,15,18,21,24,27,30,33)
        val myCalculationResult = (myArrayOfNumbers[0])/3*5
        println(myCalculationResult)

        for(number in myArrayOfNumbers){
            val z = number / 3 * 5
            println(z)
        }

        for(i in myArrayOfNumbers.indices){
            val qz = myArrayOfNumbers[i]/3*5
            println(qz)
        }

        for(b in 0..9){
            println(b)
        }

        val myStringArrayList = ArrayList<String>()
        myStringArrayList.add("Tolga")
        myStringArrayList.add("Alper")
        myStringArrayList.add("Kuş")

        for(str in myStringArrayList){
            println(str)
        }

        myStringArrayList.forEach { println(it) }

        //While Loop

        var j = 0

        while(j<10){
            j++
            println(j)
        }

    }
}