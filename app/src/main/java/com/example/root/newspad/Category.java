package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.newspad.ui.NewsListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Category extends AppCompatActivity {
    public static final String TAG = Category.class.getSimpleName();
    @Bind(R.id.button2) Button mSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
    }


    public void sports(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra(TAG ,"sports");
        startActivity(intent);
    }

    public void technology(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra(TAG ,"technology");
        startActivity(intent);
    }

    public void politics(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra(TAG ,"politics");
        startActivity(intent);
    }

    public void finance(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra(TAG ,"finance");
        startActivity(intent);

    }

    public void health(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra(TAG ,"health");
        startActivity(intent);

    }
}
