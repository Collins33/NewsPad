package com.example.root.newspad.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.newspad.R;
import com.example.root.newspad.models.News;
import com.example.root.newspad.ui.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 9/24/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {
    private Context mContext;
    private ArrayList<News> mNews=new ArrayList<>();
    private static final int MAX_WIDTH = 2500;
    private static final int MAX_HEIGHT = 1000;

    public NewsListAdapter(Context context, ArrayList<News> news){
        mContext=context;
        mNews=news;
    }
    //the view holder

    public class NewsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
          //@Bind(R.id.newsImageView) ImageView mNewsImage;
          //@Bind(R.id.newsTitle) TextView mNewsTitle;
          //@Bind(R.id.newsAuthor) TextView mNewsAuthor;
            @Bind(R.id.imageView) ImageView mNewsImage;
            @Bind(R.id.newsTitle) TextView mNewsTitle;
           private Context context;

        public NewsListViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
            context=view.getContext();
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("news", Parcels.wrap(mNews));
            context.startActivity(intent);
        }
        //bind news set contents of layout to the attributes
        public void bindNews(News news){
            mNewsTitle.setText(news.getDescription());
            //mNewsAuthor.setText(news.getAuthor());
            Picasso.with(mContext).load(news.getWebsite()).resize(MAX_WIDTH, MAX_HEIGHT).into(mNewsImage);
        }
    }
    @Override
    public NewsListAdapter.NewsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cards, parent, false);
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
