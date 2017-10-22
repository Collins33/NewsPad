package com.example.root.newspad.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.newspad.Constants;
import com.example.root.newspad.R;
import com.example.root.newspad.SavedNewsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText newsSource;
    private TextView mNewsName;
    private TextView mNewsLogo;
    private TextView bottomText;
//    private SharedPreferences mSharedPreference;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedSource;

    //for the value event listener
    private ValueEventListener msourceListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsName=(TextView) findViewById(R.id.textView);
        mNewsLogo=(TextView) findViewById(R.id.appLogo);
        bottomText=(TextView) findViewById(R.id.bottomText);
        Typeface sensation=Typeface.createFromAsset(getAssets(),"fonts/Pacifico.ttf");
        mNewsLogo.setTypeface(sensation);
        mNewsName.setTypeface(sensation);
        bottomText.setTypeface(sensation);
        //countdown timer
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(getApplicationContext(),NewsListActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
//        mSharedPreference= PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor=mSharedPreference.edit();

        mSearchedSource= FirebaseDatabase
                          .getInstance()
                          .getReference()
                          .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
        //add event listener
        msourceListener = mSearchedSource.addValueEventListener(new ValueEventListener() {
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





//    public void addToFireBase(String source){
//        mSearchedSource.push().setValue(source);
//    }
    //public void addToSharedPreference(String source){
        //mEditor.putString(Constants.PREFERENCE_SOURCE_KEY,source).apply();
    //}
    //method to remove the eventlistener
    @Override
    public void onDestroy(){
        super.onDestroy();
        mSearchedSource.removeEventListener(msourceListener);
    }
}
