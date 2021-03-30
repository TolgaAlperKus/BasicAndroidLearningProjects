package com.tolgaalperkus.instagramcloneparse;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("yMTN2t2kd1WJFVyMIPZbwiBYvjhGunGmQm9rYybp")
        .clientKey("PSKPUDgRXl12smEoxcqcSu2ESOnrT2XQcwUesP7W")
        .server("https://parseapi.back4app.com/")
        .build());
    }
}
