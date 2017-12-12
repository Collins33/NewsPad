package com.example.root.newspad.ui;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.newspad.Constants;
import com.example.root.newspad.R;
import com.example.root.newspad.models.News;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.newsTitle) TextView mNewsTitle;
    @Bind(R.id.newsDescription) TextView mNewsDescription;
    @Bind(R.id.newsAuthor) TextView mNewsAutor;
    @Bind(R.id.newsImage) ImageView mNewsImage;
    @Bind(R.id.imageShare) ImageView mNewsShare;
    @Bind(R.id.imageWeb) ImageView mGetNewsButton;
    @Bind(R.id.webPage) TextView mNewsPage;
    @Bind(R.id.imageView2) ImageView mSaveImage;

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

        Picasso.with(view.getContext()).load(mNews.getWebsite()).into(mNewsImage);
        mNewsTitle.setText(mNews.getTitle());
        mNewsDescription.setText(mNews.getDescription());
        mNewsAutor.setText(mNews.getAuthor());
        mNewsPage.setText(mNews.getImage());
        mGetNewsButton.setOnClickListener(this);
        mSaveImage.setOnClickListener(this);
        mNewsShare.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == mSaveImage) {
            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
            String uid=user.getUid();

            DatabaseReference newsRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                    .child(uid);

            DatabaseReference pushRef=newsRef.push();
            String pushId=pushRef.getKey();
            mNews.setPushId(pushId);
            pushRef.setValue(mNews);

            Toast.makeText(getContext(), "saved", Toast.LENGTH_LONG).show();
        }

        else if(v == mGetNewsButton){
            String website=mNewsPage.getText().toString();
            Intent intent=new Intent(getContext(),WebNews.class);
            intent.putExtra("website",website);
            startActivity(intent);
        }

        else if(v == mNewsShare){
            String url=mNewsPage.getText().toString();
            String mimeType="text/plain";

            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Share this story with: ")
                    .setText(url)
                    .startChooser();
        }
    }


}
