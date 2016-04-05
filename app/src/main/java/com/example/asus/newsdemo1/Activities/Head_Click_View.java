package com.example.asus.newsdemo1.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asus.newsdemo1.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by codekk on 2016/4/4.
 * Email:  645326280@qq.com
 */
public class Head_Click_View extends AppCompatActivity {
    private TextView textView;
    private SimpleDraweeView simpleDraweeView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_clicked_view);
        textView= (TextView) findViewById(R.id.textViewHeadViewClickTitle);
        simpleDraweeView= (SimpleDraweeView) findViewById(R.id.imageViewHeadViewClick);
        String imgUri = getIntent().getStringExtra("imgUri");
        String headTitle = getIntent().getStringExtra("HeadTitle");
        textView.setText(headTitle);
        simpleDraweeView.setImageURI(Uri.parse(imgUri));
    }
}
