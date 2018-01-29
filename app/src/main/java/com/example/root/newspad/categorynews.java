package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class categorynews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorynews);
        //getting the intent text
        Intent intent= getIntent();
        String message=intent.getStringExtra(Category.TAG);

        //test toast
        Toast toast= Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        toast.show();
    }
}
