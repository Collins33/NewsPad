package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText newsSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsSource= (EditText) findViewById(R.id.editText);
    }

    public void seeNews(View view){
        Intent intent=new Intent(this,secondActivity.class);
        String source= newsSource.getText().toString();
        intent.putExtra("source",source);
        startActivity(intent);
    }
}
