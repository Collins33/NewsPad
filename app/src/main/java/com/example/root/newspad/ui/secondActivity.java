package com.example.root.newspad.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.newspad.R;
import com.example.root.newspad.models.News;
import com.example.root.newspad.services.NewsService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class secondActivity extends AppCompatActivity {
     private TextView newsSource;
    private ListView mListView;

    public ArrayList<News> news = new ArrayList<>();
    public static final String TAG = secondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mListView = (ListView) findViewById(R.id.listView);
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

                 news=newsService.processResults(response);
                  Log.d(TAG,news.toString());

                  secondActivity.this.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          String[] newsNames=new String[news.size()];
                          for(int i=0;i < newsNames.length; i++){
                          newsNames[i]=news.get(i).getTitle();
                          }
                          ArrayAdapter adapter = new ArrayAdapter(secondActivity.this,
                                  android.R.layout.simple_list_item_1, newsNames);

                          mListView.setAdapter(adapter);
                      }
                      });
                  };
      });
}
}