package com.omni.cloud;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Upload_Data extends AppCompatActivity {

    ImageButton b1, b2;
    static final int abc = 123;
    ImageView imageView;
    TextView t1,t2,t3;
    ImageView i2;
    boolean flag=false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Logged Out Sucessfullly", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                return true;
            case R.id.reset:

                    ParseUser.requestPasswordResetInBackground(ParseUser.getCurrentUser().getEmail(), new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                Toast.makeText(getApplicationContext(),"Reset Successfully",Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                    return  true;

            case  R.id.info:
                        Intent i1=new Intent(Upload_Data.this,Details.class);
                        startActivity(i1);

                 //   ParseUser.requestPasswordReset("harshittrivedi8691@gmail.com",new);


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Alert!!!");
        builder.setMessage("Wanna Log Out??");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(Upload_Data.this, MainActivity.class);
                            startActivity(i);
                            //True
                        }
                        //   Toast.makeText(getApplicationContext(),"")
                    }
                });
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__data);
        if( ParseUser.getCurrentUser()!=null) {
            s = ParseUser.getCurrentUser().getUsername();
        }else {
            Intent i=new Intent(Upload_Data.this,MainActivity.class);
            startActivity(i);
        }
        setTitle("Welcome "+s);
//        t1=(TextView) findViewById(R.id.textView2);
       // t2=(TextView) findViewById(R.id.textView3);
        if(ParseUser.getCurrentUser()==null)
        {
            Intent i = new Intent(Upload_Data.this, MainActivity.class);
            startActivity(i);
        }

        ImageButton b3=(ImageButton) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Upload_Data.this,Show_Data.class);
                startActivity(i1);
            }
        });

        t3=(TextView) findViewById(R.id.textView4);
        t3.setText("Welcome "+s);
        b1 = (ImageButton) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Intent.ACTION_PICK);
                File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                String s = file.getPath();
                Uri uri = Uri.parse(s);
                i1.setDataAndType(uri, "image/*");
                startActivityForResult(i1, abc);
            }
        });

//        imageView = (ImageView) findViewById(R.id.imageView);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ParseQuery<ParseObject> pq = ParseQuery.getQuery("Image");
//                pq.whereEqualTo("user", s);
//
//                pq.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> objects, ParseException e) {
//                        if (e == null && objects != null) {
//                            for (ParseObject p : objects) {
//                                ParseFile pf = p.getParseFile("img");
//                                pf.getDataInBackground(new GetDataCallback() {
//                                    @Override
//                                    public void done(byte[] data, ParseException e) {
//                                        if (e == null && data != null) {
//                                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                                            imageView.setImageBitmap(bitmap);
//                                            Drawable  d=imageView.getDrawable();
//                                            i2.setImageDrawable(d);
//                                           // i2=imageView;
//                                        }
//                                    }
//                                });
//                            }
//                        }
//                    }
//                });

//            }
//        });

        Toast.makeText(Upload_Data.this, "Hey " + s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == abc && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            Back_Pro b1 = new Back_Pro(this, uri);

            b1.execute();
        }
    }


    class Back_Pro extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        Uri m1;
        Exception e1=null;

        public Back_Pro(Upload_Data activity, Uri uri) {
            m1 = uri;
            dialog = new ProgressDialog(activity);

        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Uploading the Image ....");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream inputStream;
                inputStream = getContentResolver().openInputStream(m1);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bout);
                byte[] b1 = bout.toByteArray();
                ParseFile f1 = new ParseFile("image.png", b1);
                ParseObject po = new ParseObject("Image");
                po.put("img", f1);
                po.put("user", ParseUser.getCurrentUser().getUsername());
                po.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            flag=true;
                            e1=e;
                            Toast.makeText(Upload_Data.this, "Uploaded Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            e1=e;
                            Toast.makeText(Upload_Data.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });

                return null;
//                imageView.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // do UI work here

            if(flag)
            {
                dialog.setMessage("Uploaded Sucessfully..");
            }
            if (dialog.isShowing()&&flag) {

                dialog.dismiss();
            }



        }
    }
}


