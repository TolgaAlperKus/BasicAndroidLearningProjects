package com.tolgaalperkus.parselearning;

import android.app.Application;

import com.parse.Parse;


public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //set log level
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("GG7sGS8J1a64U33toTQV74Qt6NTYyBPVz7gBUQ2q")
                .clientKey("fIOkMfPzvWUvxT8hND2UjC6UD2MGSCgdWHf0ZzNd")
                .server("https://parseapi.back4app.com/")
                .build()
        );



    }
}
