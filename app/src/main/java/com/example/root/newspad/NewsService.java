package com.example.root.newspad;

import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by root on 9/23/17.
 */

public class NewsService {
    public static void findNews(String source, Callback callback){
        //create the client
        OkHttpClient client = new OkHttpClient();
        //build the url plus the query
        HttpUrl.Builder urlBuilder=HttpUrl.parse("https://newsapi.org/v1/articles?sortBy=latest").newBuilder();
        urlBuilder.addQueryParameter("api",Constants.NEWS_TOKEN);
        urlBuilder.addQueryParameter(Constants.NEWS_SOURCE,source);
        //build the url
        String url=urlBuilder.build().toString();

        //make request with url
        Request request = new Request.Builder()
                .url(url)
                .build();
        //make the call
        Call call = client.newCall(request);
        call.enqueue(callback);



    }
}
