package com.example.root.newspad.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.newspad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Create_Account extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.createUserButton)
    Button mCreateUserButton;
    @Bind(R.id.nameEditText)
    EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView)
    TextView mLoginTextView;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String TAG = Create_Account.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

        ButterKnife.bind(this);

        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
        //get instance
        auth=FirebaseAuth.getInstance();
        createAuthListener();
    }
    @Override
    public void onStart(){
        super.onStart();
        //add state listener to the firebaseauth object
        auth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null) {
            auth.removeAuthStateListener(mAuthListener);
        }
    }

    //check if credentials are the right ones
    //email validation
     public boolean validateEmail(String email){
          boolean isEmailGood=(email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
         if(!isEmailGood){
             mEmailEditText.setError("Enter a valid email address");
             return false;
         }
         return isEmailGood;
     }
     //validate name
     public boolean isValidName(String name){
         if(name.equals("")){
             mNameEditText.setError("enter a valid name");
             return false;
         }
         return true;
     }
     //validate confirm password
    public boolean isValidPassword(String password,String confirm){
        if(password.length()<6){
            mPasswordEditText.setError("password must have more than 6 characters");
            return false;
        }
        else if(!password.equals(confirm)){
            mPasswordEditText.setError("password does not match");
            return false;
        }
        return true;
    }





    public void createNewUser(){
        //gather credentials
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
        //validate credentials
        boolean validEmail=validateEmail(email);
        boolean validName=isValidName(name);
        boolean validConfirm=isValidPassword(password,confirmPassword);

        if(!validName||!validEmail||!validConfirm)return;

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Authentication successful");
                }
                else{
                    Toast.makeText(Create_Account.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //method to listen for authentication
    public void createAuthListener(){
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              final FirebaseUser user=firebaseAuth.getCurrentUser();
                //check if user is null before going to main activity
                if(user != null){
                    Intent intent=new Intent(Create_Account.this,MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }
    @Override
    public void onClick(View view) {

        if (view == mLoginTextView) {
            Intent intent = new Intent(Create_Account.this, Log_In.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mCreateUserButton) {
            createNewUser();
        }

    }
}
