package com.example.teacher.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Teacher on 2017/12/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final int REQ_ADD_CONTACT = 100;
    public static final String EXTRA_KEY_NAME = "key_name";
    public static final String EXTRA_KEY_FAMILY = "key_family";
    public static final String EXTRA_KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        onLayoutIsReady(savedInstanceState,getIntent());
    }

    public abstract int getLayout();

    public abstract void onLayoutIsReady(Bundle savedInstanceState, Intent intent);
}
