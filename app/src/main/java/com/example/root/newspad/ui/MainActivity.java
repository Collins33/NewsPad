package com.example.root.newspad.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.root.newspad.Constants;
import com.example.root.newspad.R;

public class MainActivity extends AppCompatActivity {
    private EditText newsSource;
    private TextView mNewsName;
    private TextView mNewsLogo;
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

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

    }

    public void seeNews(View view){
        Intent intent=new Intent(this,NewsListActivity.class);
        String source= newsSource.getText().toString();
        intent.putExtra("source",source);
        if(!(source).equals("")) {
            addToSharedPreference(source);
        }
        addToSharedPreference(source);
        startActivity(intent);
    }
    public void addToSharedPreference(String source){
        mEditor.putString(Constants.PREFERENCE_SOURCE_KEY,source).apply();
    }
}
