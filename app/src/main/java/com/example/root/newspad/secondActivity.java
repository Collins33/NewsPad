package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class secondActivity extends AppCompatActivity {
     private TextView newsSource;
    public ArrayList<News> news = new ArrayList<>();
    public static final String TAG = secondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //recieve the intent
        Intent intent=getIntent();
        String source=intent.getStringExtra("source");
        //get the view
        newsSource=(TextView) findViewById(R.id.textView3);
        //set text
        newsSource.setText("NEWS FROM " + source);
        getNews(source);
    }


    //method to get the news
      public void getNews(String source){
          //create instance of the service
          final NewsService newsService=new NewsService();
          NewsService.findNews(source, new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                  e.printStackTrace();
              }

              @Override
              public void onResponse(Call call, Response response) throws IOException {
                  try {
                      String jsonData = response.body().string();
                      if (response.isSuccessful()) {
                          Log.v(TAG, jsonData);
                          news = newsService.processResults(response);
                      }
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          });
      }
}
