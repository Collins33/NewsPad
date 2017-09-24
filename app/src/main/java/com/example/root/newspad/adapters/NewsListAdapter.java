package com.example.root.newspad.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.newspad.R;
import com.example.root.newspad.models.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 9/24/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private Context mContext;
    private ArrayList<News> mNews=new ArrayList<>();

    public NewsListAdapter(Context context, ArrayList<News> news){
        mContext=context;
        mNews=news;
    }
    //the view holder

    public class NewsListViewHolder extends RecyclerView.ViewHolder{
          @Bind(R.id.newsImageView) ImageView mNewsImage;
          @Bind(R.id.newsTitle) TextView mNewsTitle;
          @Bind(R.id.newsAuthor) TextView mNewsAuthor;
           private Context context;

        public NewsListViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            context=view.getContext();
        }
        //bind news set contents of layout to the attributes
        public void bindNews(News news){
            mNewsTitle.setText(news.getTitle());
            mNewsAuthor.setText(news.getAuthor());
            Picasso.with(mContext).load(news.getImage()).into(mNewsImage);
        }
    }
    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);
        NewsListViewHolder viewHolder = new NewsListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.NewsListViewHolder holder, int position) {
        holder.bindNews(mNews.get(position));
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

}
