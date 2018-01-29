package com.example.root.newspad;

/**
 * Created by root on 9/23/17.
 */

public class Constants {
    public static final String NEWS_TOKEN= BuildConfig.NEWS_TOKEN;
    public static final String NEWS_SOURCE = "source";
    public static final String NEWS_CATEGORY="q";
    //QUERY TO SORT NEWS BY
    public static final String NEWS_SORT="publishedAt";
    //for adding shared preference
    public static final String PREFERENCE_SOURCE_KEY="source";
    //the key value for adding to firebase
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "news-source";
    //the node to save news to firebase
    public static final String FIREBASE_CHILD_RESTAURANTS = "news";
}
