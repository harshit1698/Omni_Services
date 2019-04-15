package com.omni.cloud;

import com.parse.Parse;
import android.app.Application;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("a5b5fbb32be5c1657796a7c9c5d1577f4bea2b07")
                // if definedY
                .clientKey("5c399c61d622e2544dfd53dae1b9c5f2ef7ea301")
                .server("http://18.222.135.166:80/parse")
                .build()
        );
    }
}