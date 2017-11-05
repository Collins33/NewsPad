package com.example.root.newspad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.newspad.ui.Create_Account;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Register extends AppCompatActivity implements View.OnClickListener{
@Bind(R.id.button3) Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        Intent intent=new Intent(getApplicationContext(), Create_Account.class);
        startActivity(intent);
        finish();
    }
}
