package com.example.root.newspad.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.newspad.R;
import com.example.root.newspad.models.News;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment {
    @Bind(R.id.newsTitle) TextView mNewsTitle;
    @Bind(R.id.newsDescription) TextView mNewsDescription;
    @Bind(R.id.newsAuthor) TextView mNewsAutor;
    @Bind(R.id.newsImage) ImageView mNewsImage;
    @Bind(R.id.saveNews) TextView mSaveNewsButton;
    @Bind(R.id.getNews) TextView mGetNewsButton;

    private News mNews;

    public static NewsDetailFragment newInstance(News news){
        //create new instance of the fragment
        NewsDetailFragment newsDetailFragment=new NewsDetailFragment();
        //create instance of bundle
        Bundle args=new Bundle();
        //put news object to the bundle by wrapping it using a Parcels
        args.putParcelable("news", Parcels.wrap(news));
        newsDetailFragment.setArguments(args);
        //return instance of the fagemnt
        return newsDetailFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //unwrap the object and save it in mNews
        mNews= Parcels.unwrap(getArguments().getParcelable("news"));
    }


    public NewsDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.fragment_news_detail, container, false);
            ButterKnife.bind(this,view);

        Picasso.with(view.getContext()).load(mNews.getImage()).into(mNewsImage);
        mNewsTitle.setText(mNews.getTitle());
        mNewsDescription.setText(mNews.getDescription());
        mNewsAutor.setText(mNews.getAuthor());



        return view;

    }

}
