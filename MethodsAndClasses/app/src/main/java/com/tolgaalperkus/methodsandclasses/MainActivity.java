package com.tolgaalperkus.methodsandclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create called");
        testMethod();
        System.out.println(math(3,5));
        System.out.println(newMethod("tolga"));
        username = "James";
        makeMusicians();
        makeSimpsons();
    }

    public void makeSimpsons(){
        Simpsons homer = new Simpsons("Homer",50,"Nucler");
        System.out.println(homer.getName());
        homer.setName("Homer Simpson");
        System.out.println(homer.getName());
    }

    public void makeMusicians(){
        Musicians james = new Musicians("James","Guitar",50);
        System.out.println(james.instrument);

    }

    public void testMethod(){
        int x = 4+4;
        username = "lars";
        System.out.println("value of x > "+ x);
    }

    public int math(int a,int b){
        username = "kirk";
        return a+b;

    }

    public String newMethod(String string){
        username = "rob";
        return string + " new method";
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("on resume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("on stop called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("on pause called");
    }
}