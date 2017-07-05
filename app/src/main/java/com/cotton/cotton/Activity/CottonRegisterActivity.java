package com.cotton.cotton.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.cotton.cotton.LoginActivity;
import com.cotton.cotton.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CottonRegisterActivity extends AppCompatActivity {

    @BindView(R.id.activity_register_email_input) EditText emailInput;
    @BindView(R.id.activity_register_username_input) EditText usernameInput;
    @BindView(R.id.activity_register_password_input) EditText passwordInput;
    @BindView(R.id.activity_register_password_confirm_input) EditText passwordConfirmInput;

    private boolean passwordConfirmed = false;

    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        this.setContentView(R.layout.activity_register);

        initFirebaseAuth();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_register_button)
    public void registerUser(View v){

        if(checkInputFilled() && this.passwordConfirmed){

            auth.createUserWithEmailAndPassword(emailInput.getText().toString().trim(), passwordInput.getText().toString().trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        //TODO Show a dialog confirming success of signing up.
                        returnToLoginActivity();

                    }else{

                        //TODO find error and display to user what error is.
                    }
                }
            });
        }
    }

    @OnTextChanged({R.id.activity_register_password_confirm_input, R.id.activity_register_password_input})
    public void confirmPasswordUpdate(){

        String password = passwordInput.getText().toString().trim();
        String passwordConfirm = passwordConfirmInput.getText().toString().trim();

        if(password.equals(passwordConfirm)){

            this.passwordConfirmed = true;
            passwordConfirmInput.setError(null);

        }else{

            passwordConfirmInput.setError("Password must match!");
        }
    }

    private void initFirebaseAuth(){

        auth = FirebaseAuth.getInstance();
    }

    private boolean checkInputFilled(){

        String email = emailInput.getText().toString().trim();
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String passwordConfirm = passwordConfirmInput.getText().toString().trim();
        boolean filled = true;

        if(email.equals("")){

            emailInput.setError("Email is not filled out!");
            filled = false;
        }

        if(username.equals("")){

            usernameInput.setError("Username is not filled out!");
            filled = false;
        }

        if(password.equals("")) {

            passwordInput.setError("Password is not filled out!");
            filled = false;
        }

        if(passwordConfirm.equals("")){

            passwordConfirmInput.setError("Please confirm your password!");
            filled = false;
        }

        return filled;
    }

    private void returnToLoginActivity(){

        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);

        startActivity(intent);
    }
}
