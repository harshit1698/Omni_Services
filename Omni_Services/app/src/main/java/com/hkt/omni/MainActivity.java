package com.hkt.omni;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b;
    TextInputEditText m1;
    TextInputEditText m2;
    boolean signup=false;
    TextView tv,tv2;

    public boolean check()
    {
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        else {
            Toast.makeText(MainActivity.this,"Kindly Grant The Permission",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.INTERNET},123);
            return false;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2=(TextView) findViewById(R.id.tv2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


      b=(Button) findViewById(R.id.button);
      m1=(TextInputEditText) findViewById(R.id.uid);
      m2=(TextInputEditText) findViewById(R.id.pwd);
      tv=(TextView) findViewById(R.id.textView);
      tv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(!signup)
              {
                  m1.setText("");
                  m2.setText("");
                  signup=true;
                  b.setText("Sign Up");
                  tv.setText("Already Exist ..Sign In Here..");
              }
              else
              {

                  m1.setText("");
                  m2.setText("");
                  signup=false;
                  b.setText("Sign In");
                  tv.setText("New User?? Sign Up Here..");
              }
          }
      });
        b.setOnClickListener(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());


    }

    @Override
    public void onClick(View v) {

        if(check()) {

            Mytask m=new Mytask(this,m1.getText().toString(),m2.getText().toString());
            m.execute();
        }


//        Toast.makeText(MainActivity.this,m1.getText().toString(),Toast.LENGTH_LONG).show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==123&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(MainActivity.this,"Thank You...",Toast.LENGTH_LONG).show();
        }
    }

    class Mytask extends AsyncTask<Void ,Void,Void>
    {
        private ProgressDialog dialog;
        String x1,x2;
        public Mytask()
        {

        }
        public Mytask(MainActivity activity,String s1,String s2) {
            x1=s1;
            x2=s2;
            dialog = new ProgressDialog(activity);

        }
        @Override
        protected void onPreExecute() {
            if(signup) {
                dialog.setMessage("Signing Up");
                dialog.show();
            }
            else {
                dialog.setMessage("Logging In");
                dialog.show();
            }
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if (signup) {
                ParseUser p = new ParseUser();
                p.setUsername(x1);
                p.setPassword(x2);
                p.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            m1.setText(x1);
                            m2.setText(x2);
                            Toast.makeText(MainActivity.this, "Sign Up Suceessful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, Upload_Data.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
            } else {
                ParseUser.logInInBackground(x1, x2, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            Intent i = new Intent(MainActivity.this, Upload_Data.class);
                            startActivity(i);

//                        Toast.makeText(MainActivity.this,"Welcome Back .. "+user.getUsername(),Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            // do UI work here

            if (dialog.isShowing() ) {
                dialog.dismiss();
            }



        }
    }
    static int i=0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this,"Log In again",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        startActivity(i);
//        finishAndRemoveTask();
//        super.onBackPressed();
    }
}
