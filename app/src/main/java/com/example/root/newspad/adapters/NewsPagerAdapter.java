package com.example.root.newspad.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.root.newspad.models.News;
import com.example.root.newspad.ui.NewsDetailFragment;

import java.util.ArrayList;

/**
 * Created by root on 9/25/17.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<News> mNews;

    public NewsPagerAdapter(FragmentManager fm, ArrayList<News> news){
        super(fm);
        mNews=news;
    }

    @Override
    public Fragment getItem(int position){
        return NewsDetailFragment.newInstance(mNews.get(position));
    }
    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mNews.get(position).getTitle();
    }

}
