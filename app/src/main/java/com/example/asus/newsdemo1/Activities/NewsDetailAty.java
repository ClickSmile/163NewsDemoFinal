package com.example.asus.newsdemo1.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.newsdemo1.Http.Retrofit.RetrofitClient;
import com.example.asus.newsdemo1.Model.NewsDetail;
import com.example.asus.newsdemo1.R;
import com.example.asus.newsdemo1.Utils.SimpleUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */

public class NewsDetailAty extends AppCompatActivity {
    private TextView textViewTitle;
    private TextView textViewContent;
    private String postId;
    private String title;
    private int imageViewCount = 0;
    private int textViewCount = 0;
    private List<NewsDetail.ImgEntity> imgs;
    private List<String> bodysList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        textViewTitle = (TextView) findViewById(R.id.textViewDetailTitle);
        textViewContent = (TextView) findViewById(R.id.textViewDetailContent);
        postId = getIntent().getStringExtra("postId");
        title = getIntent().getStringExtra("title");
        Init(postId);
    }

    private void Init(String postId) {
        Call<Map<String, NewsDetail>> newsDetail = RetrofitClient.getService().getNewsDetail(postId);
        newsDetail.enqueue(new Callback<Map<String, NewsDetail>>() {
            @Override
            public void onResponse(Call<Map<String, NewsDetail>> call, Response<Map<String, NewsDetail>> response) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutDetail);
                Map<String, NewsDetail> body = response.body();
                for (Map.Entry<String, NewsDetail> entry : body.entrySet()) {
                    imgs = entry.getValue().img;
                    imageViewCount = imgs.size();
                    textViewTitle.setText(title);
                    bodysList = SimpleUtils.makeDetailBodyToList(entry.getValue().body.toString());
                    textViewCount = bodysList.size();
                    if (textViewCount == 1) {  //表示原网页中没有图片
                        TextView textView=new TextView(getApplicationContext());
                        textView.setText(Html.fromHtml(entry.getValue().body.toString()));
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(24);
                        linearLayout.addView(textView);
                    } else {
                        TextView[] textViews = initTextViews(getApplicationContext());
                        SimpleDraweeView[] simpleDraweeViews = initImgs(getApplicationContext());
                        for (int i = 0; i < Math.min(imageViewCount, textViewCount); i++) {
                            linearLayout.addView(textViews[i]);
                            linearLayout.addView(simpleDraweeViews[i]);
                        }
                        for (int i = Math.min(imageViewCount, textViewCount); i < Math.max(imageViewCount, textViewCount); i++) {
                            linearLayout.addView(textViews[i]);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, NewsDetail>> call, Throwable t) {
                Log.d("KKLog", "NewsDetailAty onFailure===>" + t.getMessage());
            }
        });
    }

    private TextView[] initTextViews(Context applicationContext) {
        TextView[] textViews = new TextView[textViewCount];
        for (int i = 0; i < textViewCount; i++) {
            TextView textView = new TextView(applicationContext);
            textView.setText(Html.fromHtml(bodysList.get(i)));
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(24);
            textViews[i] = textView;
        }
        return textViews;
    }

    private SimpleDraweeView[] initImgs(final Context applicationContext) {
        SimpleDraweeView[] simpleDraweeViews = new SimpleDraweeView[imageViewCount];
        for (int i = 0; i < imageViewCount; i++) {
            final int index=i;
            SimpleDraweeView simpleDraweeView1 = new SimpleDraweeView(applicationContext);
            simpleDraweeView1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 380));
            simpleDraweeView1.setScaleType(ImageView.ScaleType.FIT_XY);
            simpleDraweeViews[i] = simpleDraweeView1;
            simpleDraweeView1.setImageURI(Uri.parse(imgs.get(i).src));
            simpleDraweeView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(applicationContext,Head_Click_View.class);
                    intent.putExtra("imgUri",imgs.get(index).src);
                    intent.putExtra("HeadTitle",title);
                    startActivity(intent);
                }
            });
        }
        return simpleDraweeViews;
    }
}