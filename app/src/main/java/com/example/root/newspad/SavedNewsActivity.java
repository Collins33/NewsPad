package com.example.root.newspad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.example.root.newspad.adapters.FirebaseNewsListAdapter;
import com.example.root.newspad.adapters.FirebaseNewsViewHolder;
import com.example.root.newspad.models.News;
import com.example.root.newspad.util.OnStartDragListener;
import com.example.root.newspad.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedNewsActivity extends AppCompatActivity  {
    private DatabaseReference mNewsReference;


    private FirebaseNewsListAdapter mFirebaseAdapter;


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.heading) TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        heading.setText("SAVED NEWS");

        //mNewsReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS).child(uid);
        setUpFirebaseAdapter();
    }


    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mNewsReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS).child(uid);


        mFirebaseAdapter = new FirebaseNewsListAdapter(News.class,
                R.layout.news_list, FirebaseNewsViewHolder.class,
                mNewsReference, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

}
