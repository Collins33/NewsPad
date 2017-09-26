package com.example.root.newspad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.ButterKnife;

public class SavedNewsActivity extends AppCompatActivity {
    private DatabaseReference mRestaurantNewsReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }
}
