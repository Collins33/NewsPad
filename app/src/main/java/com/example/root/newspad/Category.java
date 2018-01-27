package com.example.root.newspad;

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
        Toast toast=Toast.makeText(getApplicationContext(),"sports",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void technology(View view) {
    }

    public void politics(View view) {
    }

    public void finance(View view) {
    }

    public void health(View view) {
    }
}
