package com.example.asus.newsdemo1.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.newsdemo1.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by codekk on 2016/4/4.
 * Email:  645326280@qq.com
 */
public class Image_Clicked_Aty extends AppCompatActivity {

    private LinearLayout linearLayout;
    private PhotoViewAttacher mAttacher;
    private String imgUri;
    private String title;
    private static final int IMAGE_UPDATE = 0;

    private TextView textViewTitle;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IMAGE_UPDATE:
                    Bitmap bitmap1 = (Bitmap) msg.obj;
                    ImageView imageView1=new ImageView(Image_Clicked_Aty.this);
                    imageView1.setImageBitmap(bitmap1);
                    mAttacher = new PhotoViewAttacher(imageView1);
                    textViewTitle.setText(title);
                    imageView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    linearLayout.addView(imageView1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_clicked);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarImageClick);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textViewTitle = (TextView) findViewById(R.id.textViewClickView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayoutClickView);
        imgUri = getIntent().getStringExtra("imgUri");
        title = getIntent().getStringExtra("HeadTitle");
        if (imgUri.endsWith(".gif") || imgUri.endsWith(".GIF")) {
            loadGif();
        } else{    //有可能是jpg png 之类的都用jpg方法加载
            loadJpg();
        }
    }

    private void loadGif() {
        SimpleDraweeView simpleDraweeView1 = new SimpleDraweeView(Image_Clicked_Aty.this);
        simpleDraweeView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450));
        simpleDraweeView1.setImageURI(Uri.parse(imgUri));
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(imgUri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        simpleDraweeView1.setController(draweeController);
        linearLayout.addView(simpleDraweeView1);
        textViewTitle.setText(title);
    }

    private void loadJpg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = returnBitMap(imgUri);
                Message message = new Message();
                message.what = IMAGE_UPDATE;
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    public Bitmap returnBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
