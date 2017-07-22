package com.cotton.cotton.Handlers;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthException;

public class OnAuthFailListener implements OnFailureListener {

    private Activity activity;
    private EditText errorEditText;

    private static final String TAG = "OnAuthFailListener";

    public OnAuthFailListener(Activity activity, EditText errorEditText){

        this.activity = activity;
        this.errorEditText = errorEditText;
    }

    @Override
    public void onFailure(@NonNull Exception e) {

        //TODO Handle non FirebaseAuthExceptions gracefully somehow?
        //For example, a password that is too short.

        if(e instanceof FirebaseAuthException){

            FirebaseAuthException f = (FirebaseAuthException) e;

            final String errorCode = f.getErrorCode();
            final String localized = f.getLocalizedMessage();

            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    handleError();
                }

                private void handleError(){

                    switch(errorCode){

                        case "ERROR_USER_NOT_FOUND":
                            errorEditText.setError("User was not found.");
                            break;
                        case "ERROR_INVALID_EMAIL":
                            errorEditText.setError("Email is not properly formatted.");
                            break;
                        case "ERROR_WRONG_PASSWORD":
                            errorEditText.setError("Username and password combination is incorrect.");
                            break;
                        case "ERROR_EMAIL_ALREADY_IN_USE":
                            errorEditText.setError("This email address is already in use.");
                            break;
                    }
                }
            });
        }else{

            Log.d(TAG, "createUserWithEmail:failure", e);
        }
    }
}
