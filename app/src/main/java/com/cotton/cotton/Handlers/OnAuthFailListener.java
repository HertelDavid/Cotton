package com.cotton.cotton.Handlers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuthException;

public class OnAuthFailListener implements OnFailureListener {

    private Activity activity;
    private EditText username;

    public OnAuthFailListener(Activity activity, EditText username){

        this.activity = activity;
        this.username = username;
    }

    @Override
    public void onFailure(@NonNull Exception e) {

        FirebaseAuthException f = (FirebaseAuthException) e;

        final String errorCode = f.getErrorCode();
        final String exception = e.getMessage();

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                handleError();
            }

            private void handleError(){

                switch(errorCode){

                    case "ERROR_USER_NOT_FOUND":
                        username.setError("User was not found.");
                        break;
                    case "ERROR_INVALID_EMAIL":
                        username.setError("Email is not properly formatted.");
                        break;
                    case "ERROR_WRONG_PASSWORD":
                        username.setError("Username and password combination is incorrect.");
                }
            }
        });
    }
}
