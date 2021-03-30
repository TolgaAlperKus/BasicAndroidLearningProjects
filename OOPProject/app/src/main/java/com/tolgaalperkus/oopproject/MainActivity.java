package com.tolgaalperkus.oopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        User myUser = new User();
        myUser.name = "Tolga";
        myUser.job = "Software Engineer";

        User newUser = new User();
        newUser.name = "Kirk";
        newUser.job = "Gardener";

         */

        User myUser = new User("Tolga","Software Developer");

        //Encapsulation
        Musicians james = new Musicians ("James","Guitar",50);
        System.out.println(james.getName());
        james.setAge(60,"tolga");
        james.setAge(50,"mahmut");
        System.out.println(james.getAge());

        //Inheritance
        SuperMusician lars = new SuperMusician("Lars","Drums",55);
        System.out.println(lars.sing());
        System.out.println(lars.getAge());

        //Polymorphism
            //Static Polymorphism
        Mathematics mathematics = new Mathematics();
        System.out.println(mathematics.sum());
        System.out.println(mathematics.sum(5,7));
        System.out.println(mathematics.sum(5,7,1));

            //Dynamic Polymorphism
        Animal myAnimal = new Animal();
        myAnimal.sing();

        Dog barley = new Dog();
        barley.test();
        barley.sing();

        //Inheritance
        System.out.println(myUser.information());

        //Abstract & fInterface
        Piano myPiano = new Piano();
        myPiano.brand = "Yamaha";
        myPiano.digital = true;
        myPiano.info();
    }
}