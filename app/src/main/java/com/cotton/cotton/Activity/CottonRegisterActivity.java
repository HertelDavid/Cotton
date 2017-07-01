package com.cotton.cotton.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cotton.cotton.R;

import butterknife.ButterKnife;

public class CottonRegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);
        this.setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }
}
