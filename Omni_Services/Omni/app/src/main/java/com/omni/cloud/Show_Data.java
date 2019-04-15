package com.omni.cloud;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class Show_Data extends AppCompatActivity {

    GridView gv;
    String t="";
    //    ScrollView sv;
    List<ParseObject> l1; String s1;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0,"Delete");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Delete")
        {
            gv.getSelectedItemPosition();
            Toast.makeText(getApplicationContext(),"Deleted Successfully"+gv.getSelectedItemPosition(),Toast.LENGTH_LONG).show();
            //  l1.get()
        }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__data);
        setTitle("Photos Of "+ParseUser.getCurrentUser().getUsername()+" :))");
        gv=(GridView) findViewById(R.id.grid);
        registerForContextMenu(gv);
//        sv=(ScrollView) findViewById(R.id.sv);
//        sv.addView(gv);

        s1= ParseUser.getCurrentUser().getUsername();
        My my=new My(this);
        my.execute();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ParseFile pf=l1.get(position).getParseFile("img");

                pf.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        if(data!=null&&e==null)
                        {
                            Intent intent=new Intent(Show_Data.this,Show.class);

                            try
                            {
                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                ByteArrayOutputStream bout=new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,bout);
                                intent.putExtra("stream",bout.toByteArray());
                               // intent.putExtra("title",t);
//                                intent.putExtra("title",t);
                                startActivity(intent);
                            }  catch(Exception x)
                            {
                                Toast.makeText(getApplicationContext(),x.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
            }
        });



    }
    class My extends AsyncTask<Void,Void,Void>
    {
        private ProgressDialog dialog;
        public My(Show_Data activity) {
//            m1 = uri;
            dialog = new ProgressDialog(activity);

        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Getting the Images ....");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ParseQuery<ParseObject> pq = ParseQuery.getQuery("Image");
            pq.whereEqualTo("user", s1);
            // pq.whereEqualTo("flag",0);
            pq.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e==null)
                    {
                        l1=objects;
                        I_ad i1=new I_ad(getApplicationContext(),l1);
                        gv.setAdapter(i1);
                    }
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // do UI work here

            if (dialog.isShowing()) {
                dialog.dismiss();
            }



        }
    }
}
class I_ad extends BaseAdapter {

    Context c1;
    List<ParseObject> p1;

    public I_ad(Context c, List<ParseObject> b)
    {
        c1=c;
        p1=b;
    }
    @Override
    public int getCount() {
        return p1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void add(int x)
    {
        int q=this.getCount();
//        im[q]=x;
        // this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        if(convertView==null)
        {
            imageView=new ImageView(c1);
            imageView.setLayoutParams(new GridView.LayoutParams(535,520));
            imageView.setPadding(3,3,3,3);
//            imageView
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }
        else {
            imageView=(ImageView)convertView;
        }
        ParseFile pf=p1.get(position).getParseFile("img");

        pf.getDataInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, ParseException e) {
                if(e==null&&data!=null)
                {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    imageView.setImageBitmap(bitmap);

                }
                else {
//                    Toast.makeText(Show_Data.this,"",)
                }
            }
        });

        return imageView;
    }
}