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
import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class News_fragment extends Fragment {

    @Bind(R.id.newsImage) ImageView mImageLabel;
    @Bind(R.id.newsTitle)
    TextView mNewsTitle;
    @Bind(R.id.newsAuthor)  TextView mNewsAuthor;
    @Bind(R.id.newsDescription)  TextView mNewsDescription;
    @Bind(R.id.saveNewstButton)  TextView  mSaveNews;
    @Bind(R.id.viewNewsButton) TextView  mViewNews;

    private News mNews;
    public static News_fragment newInstances(News news){
        //instance of fragment
        News_fragment newsFragment=new News_fragment();
        //create new bundle
        Bundle arg=new Bundle();
        //put news in bundle
        arg.putParcelable("news", Parcels.wrap(news));
        //set bundle as argument
        newsFragment.setArguments(arg);
        //return the fragment
        return newsFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //unwrap our news object
        mNews = Parcels.unwrap(getArguments().getParcelable("news"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        ButterKnife.bind(this, view);
        Picasso.with(view.getContext()).load(mNews.getImage()).into(mImageLabel);

        mNewsTitle.setText(mNews.getTitle());
        mNewsAuthor.setText(mNews.getAuthor());
        mNewsDescription.setText(mNews.getDescription());

        return view;
    }


}
