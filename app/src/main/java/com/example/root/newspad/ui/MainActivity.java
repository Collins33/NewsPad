package com.example.root.newspad.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.newspad.Constants;
import com.example.root.newspad.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText newsSource;
    private TextView mNewsName;
    private TextView mNewsLogo;
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsSource= (EditText) findViewById(R.id.editText);
        mNewsName=(TextView) findViewById(R.id.textView);
        mNewsLogo=(TextView) findViewById(R.id.appLogo);
        Typeface sensation=Typeface.createFromAsset(getAssets(),"fonts/sensation.ttf");
        mNewsLogo.setTypeface(sensation);
        mNewsName.setTypeface(sensation);

        mSharedPreference= PreferenceManager.getDefaultSharedPreferences(this);
        mEditor=mSharedPreference.edit();

        mSearchedSource= FirebaseDatabase
                          .getInstance()
                          .getReference()
                          .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
        //add event listener
        mSearchedSource.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for(DataSnapshot source:dataSnapshot.getChildren()){
                   String newSource= source.getValue().toString();
                   Log.d("new source", "updated source "+ newSource);
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void seeNews(View view){
        Intent intent=new Intent(this,NewsListActivity.class);
        String source= newsSource.getText().toString();
        intent.putExtra("source",source);

        //addToSharedPreference(source);
        startActivity(intent);
        addToFireBase(source);
    }
    public void addToFireBase(String source){
        mSearchedSource.push().setValue(source);
    }
    //public void addToSharedPreference(String source){
        //mEditor.putString(Constants.PREFERENCE_SOURCE_KEY,source).apply();
    //}
}
