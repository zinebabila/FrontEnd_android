package com.example.tpjsonimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {


    ArrayList<Livre> livres=new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask asyncTask =new MyAsyncTask();
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(image);
        listView=findViewById(android.R.id.list);

        asyncTask.execute("http://172.17.36.195:45455/api/Livres");
    }
    class MyAsyncTask extends AsyncTask<String, String, String> {
        String newData="";


        @Override
        protected void onPostExecute(String s) {
            new Adapter(livres,(RecyclerView) findViewById(R.id.resc));
           // Adapter adap = new Adapter((Activity) getApplicationContext(), livres);

           // listView.setAdapter(adap);

        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Open connection.");
            String s="";
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.connect();
                publishProgress("start reading!!");
                InputStream in=new BufferedInputStream(urlConnection.getInputStream());
                newData=Stream2String(in);

                //StringReader fis = new StringReader(newData);
                //JSONObject myJson=new JSONObject(newData);
                JSONArray myarray=new JSONArray(newData);
                //myarray.length();
               // JSONArray arrayJ=myJson.getJSONArray("items");
               // JSONObject Jobj1=arrayJ.getJSONObject(2);
                for(int i=0;i<myarray.length();i++){
                    JSONObject obj=myarray.getJSONObject(i);
                    String nom=obj.getString("nom");
                    String image=obj.getString("image");
                    livres.add(new Livre(nom,image));

                }




            }catch (Exception exp){
                publishProgress("cannot connect to server");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @SuppressLint("WrongThread")
        @Override
        protected void onPreExecute() {
            newData="";
            publishProgress("connecting attempt ongoing please wait !.");
        }
        public String Stream2String(InputStream in){
            BufferedReader buReader=new BufferedReader(new InputStreamReader(in));
            String text="",line;
            try {
                while ((line=buReader.readLine())!=null){
                    text+=line;
                }
            }catch (Exception exp){}
            System.out.println(text);
            System.out.println("****************************************************");
            return text;
        }
    }
}