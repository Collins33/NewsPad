package com.example.root.newspad.services;

import com.example.root.newspad.Constants;
import com.example.root.newspad.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by root on 9/23/17.
 */

public class NewsService {
    public static void findNews(String source, Callback callback){
        //create the client
        OkHttpClient client = new OkHttpClient();
        //build the url plus the query
        HttpUrl.Builder urlBuilder=HttpUrl.parse("https://newsapi.org/v1/articles?").newBuilder();
        urlBuilder.addQueryParameter("apiKey", Constants.NEWS_TOKEN);
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

    //FINDING NEWS ACCORDING TO CATEGORY
    public static void findByCategory(String category, Callback callback){
        //create the client
        OkHttpClient client=new OkHttpClient();
        //build url plus the query
        HttpUrl.Builder urlBuilder=HttpUrl.parse("https://newsapi.org/v2/everything?").newBuilder();
        //add the parameters
        urlBuilder.addQueryParameter("apikey",Constants.NEWS_TOKEN);
        urlBuilder.addQueryParameter("sortBy",Constants.NEWS_SORT);

        //build final url
        String url=urlBuilder.build().toString();

        //make request with the url
        Request request=new Request.Builder()
                .url(url)
                .build();

        //make the api call with the built url
        Call call=client.newCall(request);
        call.enqueue(callback);
    }

    //process the results
    public  ArrayList<News> processResults(Response response){
        //create arraylist to contain the news
        ArrayList<News> news=new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                //create new instance of jsonobject and pass jsondata as argument
                JSONObject newsaoabject=new JSONObject(jsonData);
                //articles is the name of array we want
                JSONArray newsArray= newsaoabject.getJSONArray("articles");
                 //loop through the array
                for(int i=0; i<=newsArray.length(); i++){
                    JSONObject newsJSON = newsArray.getJSONObject(i);
                    //get the properties

                    String author=newsJSON.getString("author");
                    String title=newsJSON.getString("title");
                    String description=newsJSON.getString("description");
                    String url=newsJSON.getString("url");
                    String urlToImage=newsJSON.getString("urlToImage");
                    String publishedAt=newsJSON.getString("publishedAt");

                    //create new news object
                    News myNews=new News(author,title,description,url,urlToImage,publishedAt);
                    //add object to array
                    news.add(myNews);
                }

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }
}
