package com.example.root.newspad.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.root.newspad.R;

public class WebNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_news);


        Intent intent = getIntent();
        String site = intent.getStringExtra("website");

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.loadUrl(site);
    }
}
