package com.example.asus.newsdemo1.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ASUS on 2016/4/1.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inits();
    }

    public void inits() {
        setContentView();
        findViews();
        getData();
        bindData();
    }


    public abstract void setContentView();

    public abstract void findViews();

    public abstract void getData();

    public abstract void bindData();
}
