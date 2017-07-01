package com.cotton.cotton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cotton.cotton.Activity.CottonRegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_username) EditText usernameEditText;
    @BindView(R.id.activity_login_password) EditText passwordEditText;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        initFirebaseAuth();
        ButterKnife.bind(this);
    }

    @Override
    public void onStart(){

        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop(){

        super.onStop();

        if(authStateListener != null){
            auth.removeAuthStateListener(authStateListener);
        }
    }

    @OnClick(R.id.activity_login_signin_button)
    public void loginUser(View v){

        confirmCredentials(usernameEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.activity_login_register_button)
    public void goToRegisterUserActivity(View v) {

        Intent registerActivityIntent = new Intent();
        registerActivityIntent.setClass(this, CottonRegisterActivity.class);

        startActivity(registerActivityIntent);
    }

    @OnClick(R.id.activity_login_forgot_info_wrapper)
    public void forgotInfo(View v){

        System.out.println("Reached");
    }

    private void confirmCredentials(String username, String password){

        System.out.println(username);
        System.out.println(password);

        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    System.out.println("Yes, the registration was successful");
                }else{

                    System.out.println("No, the registration was not successful");
                }
            }
        });
    }

    private void loginFailed(){


    }

    private void loginSuccess(){


    }

    private void initFirebaseAuth(){

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){

                    //User is logged in.
                    System.out.println("User is logged in");

                }else{

                    //User is logged out.
                    //User should not have access to app
                }
            }
        };

    }
}
