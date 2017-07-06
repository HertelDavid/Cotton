package com.cotton.cotton.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cotton.cotton.R;

public class CottonActivity extends AppCompatActivity {

    protected ProgressDialog progress;

    protected void showProgressDialogNoBack(){

        progress = new ProgressDialog(this, R.style.DialogCustomTheme);
        progress.setCancelable(false);
        progress.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progress.show();
    }

    protected void showProgressDialogTitle(String title){

        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setCancelable(false);
        progress.setTitle(title);
        progress.show();
    }

    protected void showProgressDialogTitleAndMessage(String title, String message){

        progress = new ProgressDialog(this);
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setCancelable(false);
        progress.show();
    }

    protected void cancelProgressDialog(){

        this.progress.cancel();
    }
}
