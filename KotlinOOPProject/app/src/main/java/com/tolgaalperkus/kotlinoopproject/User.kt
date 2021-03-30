package com.tolgaalperkus.kotlinoopproject

class User {

    //property
    var name : String? = null
    var age : Int? = null
    //constructor
    constructor(name : String , age : Int){
        this.name = name
        this.age = age
        println("user created")
    }
    init {
        println("init running")
    }
}