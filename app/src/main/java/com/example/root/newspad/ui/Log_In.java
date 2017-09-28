package com.example.root.newspad.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.root.newspad.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Log_In extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);

        ButterKnife.bind(this);
        mRegisterTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mRegisterTextView) {
            Intent intent = new Intent(Log_In.this, Create_Account.class);
            startActivity(intent);
            finish();
        }
    }
}
