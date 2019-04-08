package com.hkt.omni;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Show extends AppCompatActivity {

    ImageView iv;
    List<ParseObject> l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        setTitle("Photo");
//        Integer p=getIntent().getExtras().getInt("pos");

            byte[] data = getIntent().getByteArrayExtra("stream");
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            iv = (ImageView) findViewById(R.id.imageView3);
            iv.setImageBitmap(bitmap);


    }
}
