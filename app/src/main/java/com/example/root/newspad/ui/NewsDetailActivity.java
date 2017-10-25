package com.example.root.newspad.ui;

import android.graphics.Typeface;
import android.os.Parcel;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;


import com.example.root.newspad.R;
import com.example.root.newspad.adapters.NewsPagerAdapter;
import com.example.root.newspad.models.News;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {
     @Bind(R.id.viewPager) ViewPager mViewPager;
    private NewsPagerAdapter newsPagerAdapter;
    ArrayList<News> mNews=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        mNews= Parcels.unwrap(getIntent().getParcelableExtra("news"));
        int startingPosition = getIntent().getIntExtra("position",0);
        newsPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), mNews);
        mViewPager.setAdapter(newsPagerAdapter);
        mViewPager.setCurrentItem(startingPosition);



    }
}
