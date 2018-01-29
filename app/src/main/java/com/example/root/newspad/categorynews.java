package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.root.newspad.services.NewsService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class categorynews extends AppCompatActivity {
    public static final String TAG = categorynews.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorynews);
        //getting the intent text
        Intent intent= getIntent();
        String message=intent.getStringExtra(Category.TAG);
        getNewsByCategory(message);

        //test toast
        Toast toast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        toast.show();
    }

    //method to get news by category
    private void getNewsByCategory(String category){
        final NewsService newsService=new NewsService();
        newsService.findByCategory(category, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              try{
                 String jsonData=response.body().string();
                  Log.v(TAG,jsonData);
              }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
