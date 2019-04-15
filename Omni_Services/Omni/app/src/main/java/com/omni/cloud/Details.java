package com.omni.cloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;

public class Details extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        ArrayList<String> al=new ArrayList<String>();
//        al.add(ParseUser.getCurrentUser().getUsername());
        String s1[]={"UserName: "+ParseUser.getCurrentUser().getUsername()};
        ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,s1);
        lv=findViewById(R.id.list);
        lv.setAdapter(ad);
    }
}
