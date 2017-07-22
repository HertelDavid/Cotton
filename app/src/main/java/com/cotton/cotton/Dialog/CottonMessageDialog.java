package com.cotton.cotton.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cotton.cotton.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CottonMessageDialog extends DialogFragment {

    @BindView(R.id.dialog_cotton_message_title) TextView titleTextView;
    @BindView(R.id.dialog_cotton_message) TextView messageTextView;
    @BindView(R.id.dialog_cotton_message_button) Button messageButton;

    private final static String MESSAGE_ARGUMENT_KEY = "message";
    private final static String TITLE_ARGUMENT_KEY = "title";
    private final static String BUTTON_TEXT_ARGUMENT_KEY = "button";
    private final static String TAG = "CottonMessageDialog";

    private CottonMessageDialogListener listener;

    public interface CottonMessageDialogListener{
        public void cottonMessageDialogButtonPressed();
    }

    public static CottonMessageDialog getCottonMessageDialogInstance(String message, String title, String buttonText){

        //Create a new instance
        CottonMessageDialog messageDialog = new CottonMessageDialog();

        //Create the arguments
        Bundle arguments = new Bundle();
        arguments.putString(CottonMessageDialog.MESSAGE_ARGUMENT_KEY, message);
        arguments.putString(CottonMessageDialog.TITLE_ARGUMENT_KEY, title);
        arguments.putString(CottonMessageDialog.BUTTON_TEXT_ARGUMENT_KEY, buttonText);

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
        this.changeFields();
        return builder.create();
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);

        if(context instanceof CottonMessageDialogListener){

            this.listener = (CottonMessageDialogListener) context;
        }
    }

    @OnClick(R.id.dialog_cotton_message_button)
    public void backToLogin(View v){

        this.listener.cottonMessageDialogButtonPressed();
    }

    private void changeFields(){

        messageTextView.setText(getArguments().getString(CottonMessageDialog.MESSAGE_ARGUMENT_KEY));
        titleTextView.setText(getArguments().getString(CottonMessageDialog.TITLE_ARGUMENT_KEY));
        messageButton.setText(getArguments().getString(CottonMessageDialog.BUTTON_TEXT_ARGUMENT_KEY));
    }
}
