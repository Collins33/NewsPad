package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {
     private TextView newsSource;
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
    }
}
