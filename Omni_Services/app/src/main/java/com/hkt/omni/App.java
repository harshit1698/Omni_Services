package com.hkt.omni;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Paste Your App id  Here")
                // if definedY
                .clientKey("Paste Your Client key  Here")
                .server("Paste Your server Here")
                .build()
        );

        ParseUser.logOut();

//        ParseUser.logOut();
//        ParseUser.logInInBackground(t1 ,t2, new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException e) {
//
//                if(user!=null&&e==null)
//                {
//                    Toast.makeText(App.this,"Welcome ",Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });


        // Making Parse Object
//        ParseObject o=new ParseObject("User_Info");
//        o.put("u_id","harshit");
//        o.put("pwd","H@123");
//        o.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null)
//                {
//                    Log.i("Result Harshit ","Done");
//                   // Toast.makeText()
//                }
//                else {
//                    Log.i("Result Harshit ","Failed");
//                }
//            }
//        });

        //Parsing the Query

//        ParseQuery<ParseObject> query=ParseQuery.getQuery("User_Info");
//        query.getInBackground("oBrRBJ69nr", new GetCallback<ParseObject>() {
//            @Override
//            public void done(ParseObject object, ParseException e) {
//                object.put("u_id","Pihu");
//                object.saveInBackground();
//                if(object!=null&&e==null)
//                {
//                    Log.i("Welcome ",object.getString("u_id"));
////                    Toast.makeText(App.this,"Welcome "+ (object.getString("u_id")),Toast.LENGTH_LONG).show();
//                }
//            }
//        });

//        ParseQuery<ParseObject> query=ParseQuery.getQuery("User_Info");
//        query.whereStartsWith("u_id","P");
//        query.whereContains("u_id","Pih");

        //Find In Background
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e==null)
//                {
//                    for (ParseObject o : objects)
//                    {
//                        o.put("u_id",o.getString("u_id")+" Trivedi");
//                        o.saveInBackground();
////                        Log.i("U_id",o.getString("u_id"));
//                    }
//                }
//            }
//        });

//        ParseUser.logOut();

        //Get Current User

//        if(ParseUser.getCurrentUser()!=null)
//        {
//            Log.i("Login ","Logged in "+ParseUser.getCurrentUser().getUsername());
//        }
//        else {
//            Log.i("Login ","Logged Out");
//        }

        //Log In Background...

//        ParseUser.logInInBackground("hp_2324", "pihu@123", new LogInCallback() {
//            @Override
//            public void done(ParseUser user, ParseException e) {
//                if(user!=null)
//                {
//
//                    Log.i("Welcome Back ",user.getUsername());
//                }
//            }
//        });



//ParseObject o1=new ParseObject("User_Info");
//o1.put("u_id","h1");
//o1.put("pwd","h2123");
//o1.saveInBackground(new SaveCallback() {
//    @Override
//    public void done(ParseException e) {
//        if(e==null)
//        {
//            Log.i("Yo","Yp");
//
//        }
//        else {
//            Log.i("Nope","No");
//        }
//    }
//});
//
//        final ParseUser parseUser=new ParseUser();
//        parseUser.setEmail("pihutrive94@gmail.com");
//        parseUser.setUsername("hp_23");
//        parseUser.setPassword("pihu@123");
//        parseUser.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null)
//                {
//                    Log.i("Sign Up ","Successful");
//                }
//                else {
//                    Log.i("Error" ,e.getMessage());
//                    Log.i("Sign Up "," Failure");
//                }
//            }
//        });


        //It'll Make Automatic user
//        ParseUser.enableAutomaticUser();
    }
}