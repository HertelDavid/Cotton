package com.cotton.cotton.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cotton.cotton.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CottonMessageDialog extends DialogFragment {

    @BindView(R.id.dialog_cotton_message) TextView messageTextView;

    final static String messageArgumentKey = "message";

    public static CottonMessageDialog getCottonMessageDialogInstance(String message){

        //Create a new instance
        CottonMessageDialog messageDialog = new CottonMessageDialog();

        //Create the arguments
        Bundle arguments = new Bundle();
        arguments.putString(CottonMessageDialog.messageArgumentKey, message);

        //Set the arguments for the newly created instance
        messageDialog.setArguments(arguments);

        return messageDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Inflate and give view to the Builder.
        View v = View.inflate(getActivity(), R.layout.dialog_cotton_message, null);
        builder.setView(v);

        //Bind the View.
        ButterKnife.bind(this, v);

        //Change the message now that the view has been bound.
        this.changeMessage();
        return builder.create();
    }

    @OnClick(R.id.dialog_cotton_message_button)
    public void backToLogin(View v){


    }

    private void changeMessage(){

        messageTextView.setText(getArguments().getString(CottonMessageDialog.messageArgumentKey));
    }
}
