package com.omni.cloud;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Show extends AppCompatActivity {

    ImageView iv;
    List<ParseObject> l1;
    Bitmap bitmap;
    String s11;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.download, menu);
        return true;
    }

    public boolean save()
    {
        Double d=Math.random();
        String p=d.toString();
        File Pic_dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        String name="omni_"+s11+"_"+p+".jpg";
        File i_file=new File(Pic_dir,name);
        try {
            FileOutputStream fout=null;
            if(!i_file.exists()) {
                i_file.createNewFile();
                fout=new FileOutputStream(i_file);
                //count++;
            }
            else {
                File f1=new File(Pic_dir,name);f1.createNewFile();
                fout=new FileOutputStream(f1);
                //  count++;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fout);
            Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
            fout.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download:
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

                        boolean b=save();
                        return b;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Kindly Grant The Permission",Toast.LENGTH_LONG).show();
                       ActivityCompat.requestPermissions(Show.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
                        return false;
                    }



        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==123)
        {
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                save();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        setTitle("Photo");
//        Integer p=getIntent().getExtras().getInt("pos");
//        String s1=getIntent().getExtras().get("title");

        byte[] data = getIntent().getByteArrayExtra("stream");
           // String s1=getIntent().getExtras().getString("title");
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        iv = (ImageView) findViewById(R.id.imageView3);
        iv.setImageBitmap(bitmap);
        Date date=new Date();
        int i=date.getDate();
        Integer i1=date.getSeconds();
         s11=i1.toString();



     //   Toast.makeText(getApplicationContext(),"Date is "+s11,Toast.LENGTH_LONG).show();


    }
}
