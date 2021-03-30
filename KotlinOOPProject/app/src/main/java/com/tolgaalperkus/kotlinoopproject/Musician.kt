package com.tolgaalperkus.kotlinoopproject

open class Musician(name: String, instrument: String, age: Int) {
    var name : String? = name
        private set
        get
    var age : Int? = age
        private set
        get
    private val bandName : String = "Metallica"
    fun returnBandName ( password: String) : String {
        if(password == "Atil"){
            return bandName
        }else{
            return "Wrong password!"
        }
    }
}