package com.example.root.newspad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.root.newspad.adapters.FirebaseNewsViewHolder;
import com.example.root.newspad.models.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedNewsActivity extends AppCompatActivity {
    private DatabaseReference mNewsReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        mNewsReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<News, FirebaseNewsViewHolder>
                (News.class, R.layout.news_list, FirebaseNewsViewHolder.class,
                        mNewsReference) {

            @Override
            protected void populateViewHolder(FirebaseNewsViewHolder viewHolder,
                                              News model, int position) {
                viewHolder.bindNews(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
