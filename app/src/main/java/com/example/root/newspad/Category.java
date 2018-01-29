package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Category extends AppCompatActivity {
    @Bind(R.id.button2) Button mSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
    }


    public void sports(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra("sports","sports");
        startActivity(intent);
    }

    public void technology(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra("sports","sports");
        startActivity(intent);
    }

    public void politics(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra("sports","sports");
        startActivity(intent);
    }

    public void finance(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra("sports","sports");
        startActivity(intent);

    }

    public void health(View view) {
        Intent intent=new Intent(getApplicationContext(),categorynews.class);
        intent.putExtra("sports","sports");
        startActivity(intent);

    }
}
