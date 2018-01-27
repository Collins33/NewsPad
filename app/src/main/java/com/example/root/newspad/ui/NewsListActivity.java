package com.example.root.newspad.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.newspad.Category;
import com.example.root.newspad.Constants;
import com.example.root.newspad.R;
import com.example.root.newspad.SavedNewsActivity;
import com.example.root.newspad.adapters.NewsListAdapter;
import com.example.root.newspad.models.News;
import com.example.root.newspad.services.NewsService;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsListActivity extends AppCompatActivity {
     private TextView newsSource;
     private ListView mListView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mSource;

    public ArrayList<News> news = new ArrayList<>();
    public static final String TAG = NewsListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private NewsListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //recieve the intent
        Intent intent=getIntent();

        //get the view

        //set text

        Typeface senasation=Typeface.createFromAsset(getAssets(),"fonts/sensation.ttf");

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        mSource=mSharedPreferences.getString(Constants.PREFERENCE_SOURCE_KEY,null);
        TextView heading=(TextView) findViewById(R.id.heading);
        String headline= heading.getText().toString();

        Typeface style=Typeface.createFromAsset(getAssets(),"fonts/sensation.ttf");
        heading.setTypeface(style);
        //initial news sources

        List<String> newsSources=new ArrayList<String>();
        newsSources.add("techcrunch");
        newsSources.add("bbc-news");
        newsSources.add("cnn");
        newsSources.add("buzzfeed");
        newsSources.add("espn");
        newsSources.add("mashable");
        newsSources.add("recode");
        newsSources.add("techradar");
        Random randomNumber=new Random();
        int newsSource=randomNumber.nextInt(8);

        //Log.d("shared pref",mSource);
        if (mSource != null) {
            getNews(newsSources.get(newsSource));

        }
        if(heading != null){
            heading.setText(newsSources.get(newsSource));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        inflater.inflate(R.menu.menu_main,menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView heading=(TextView) findViewById(R.id.heading);
                heading.setText(query);
                addToSharedPreferences(query);
                getNews(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.action_logout){
            logout();
            return true;
        }
        else if(id == R.id.aboutUS){
            Intent intent=new Intent(getApplicationContext(),AboutUs.class);
            startActivity(intent);
        }
        else if(id==R.id.savedNews){
            Intent intent = new Intent(NewsListActivity.this, SavedNewsActivity.class);
            startActivity(intent);
        }
        else if(id ==R.id.category){
            Intent intent=new Intent(getApplicationContext(), Category.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(NewsListActivity.this, Log_In.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void addToSharedPreferences(String source) {
        mEditor.putString(Constants.PREFERENCE_SOURCE_KEY, source).apply();
    }



    //method to get the news
      public void getNews(String source){
          //create instance of the service
          final NewsService newsService=new NewsService();
          //use the instance to access the findNews()
          NewsService.findNews(source, new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                  e.printStackTrace();
              }

              @Override
              public void onResponse(Call call, Response response) throws IOException {

                 news=newsService.processResults(response);
                  Log.d(TAG,news.toString());

                  NewsListActivity.this.runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          /**
                          String[] newsNames=new String[news.size()];
                          for(int i=0;i < newsNames.length; i++){
                          newsNames[i]=news.get(i).getAuthor();
                          }
                          ArrayAdapter adapter = new ArrayAdapter(NewsListActivity.this,
                                  android.R.layout.simple_list_item_1, newsNames);

                          mListView.setAdapter(adapter);**/

                          mAdapter = new NewsListAdapter(getApplicationContext(), news);
                          mRecyclerView.setAdapter(mAdapter);
                          RecyclerView.LayoutManager layoutManager =
                                  new LinearLayoutManager(NewsListActivity.this);
                          mRecyclerView.setLayoutManager(layoutManager);
                          mRecyclerView.setHasFixedSize(true);
                      }
                      });
                  };
      });
}
}