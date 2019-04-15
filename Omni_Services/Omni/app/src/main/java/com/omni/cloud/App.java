package com.omni.cloud;

import com.parse.Parse;
import android.app.Application;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Paste Your Application Id Here")
                // if definedY
                .clientKey("paste your client key here")
                .server("pastse Your Url Here")
                .build()
        );
    }
}
