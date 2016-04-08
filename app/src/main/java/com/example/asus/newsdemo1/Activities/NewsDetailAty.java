package com.example.asus.newsdemo1.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.LoginFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.asus.newsdemo1.Http.Retrofit.RetrofitClient;
import com.example.asus.newsdemo1.Model.NewsDetail;
import com.example.asus.newsdemo1.R;
import com.example.asus.newsdemo1.Utils.SimpleUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
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

public class NewsDetailAty extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewTitle;
    private TextView textViewContent;
    private String postId;
    private String title;
    private int imageViewCount = 0;
    private int textViewCount = 0;
    private List<NewsDetail.ImgEntity> imgs;
    private List<String> bodysList;

    private ImageView imageViewBottom;
    private TextView textViewBottomCount;
    private String replyCount;

    private LinearLayout linearLayoutBottom;

    //查看评论要用的参数
    private String replyBoard=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarNewsDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textViewTitle = (TextView) findViewById(R.id.textViewDetailTitle);
        postId = getIntent().getStringExtra("postId");
        title = getIntent().getStringExtra("title");
        replyCount = getIntent().getStringExtra("replyCount");
        imageViewBottom= (ImageView) findViewById(R.id.imageViewBottomViewPinglun);
        textViewBottomCount= (TextView) findViewById(R.id.textViewBottomViewCount);
        linearLayoutBottom= (LinearLayout) findViewById(R.id.linearLayoutBottom);
        Init(postId);
    }

    private void Init(final String postId) {
        Call<Map<String, NewsDetail>> newsDetail = RetrofitClient.getService().getNewsDetail(postId);
        newsDetail.enqueue(new Callback<Map<String, NewsDetail>>() {
            @Override
            public void onResponse(Call<Map<String, NewsDetail>> call, Response<Map<String, NewsDetail>> response) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutDetail);
                linearLayout.setPadding(4,10,4,4);
                Map<String, NewsDetail> body = response.body();
                for (Map.Entry<String, NewsDetail> entry : body.entrySet()) {
                    NewsDetail d=entry.getValue();
                    replyBoard=d.replyBoard;
                    imgs = entry.getValue().img;
                    imageViewCount = imgs.size();
                    textViewTitle.setText(title);
                    bodysList = SimpleUtils.makeDetailBodyToList(entry.getValue().body.toString());
                    textViewCount = bodysList.size();
                    if (textViewCount == 1) {  //表示原网页中没有图片
                        TextView textView = new TextView(getApplicationContext());
                        textView.setText(Html.fromHtml(entry.getValue().body.toString()));
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(24);
                        linearLayout.addView(textView);
                    } else {
                        TextView[] textViews = initTextViews(getApplicationContext());
                        SimpleDraweeView[] simpleDraweeViews = initImgs(getApplicationContext());
                        for (int i = 0; i < Math.min(imageViewCount, textViewCount); i++) {
                            textViews[i].setPadding(0,10,0,8);
                            linearLayout.addView(textViews[i]);
                            linearLayout.addView(simpleDraweeViews[i]);
                            ViewGroup.LayoutParams pam=  simpleDraweeViews[i].getLayoutParams();
                            pam.height=Integer.parseInt(imgs.get(i).pixel.substring(0,3));
                            pam.width=pam.MATCH_PARENT;
                            simpleDraweeViews[i].setLayoutParams(pam);
                        }
                        for (int i = Math.min(imageViewCount, textViewCount); i < Math.max(imageViewCount, textViewCount); i++) {
                            textViews[i].setPadding(0,14,0,8);
                            linearLayout.addView(textViews[i]);
                        }
                    }
                }
                textViewBottomCount.setText(replyCount);
                textViewBottomCount.setOnClickListener(NewsDetailAty.this);
                imageViewBottom.setOnClickListener(NewsDetailAty.this);
                linearLayoutBottom.setOnClickListener(NewsDetailAty.this);
            }

            @Override
            public void onFailure(Call<Map<String, NewsDetail>> call, Throwable t) {
                Log.d("KKLog", "NewsDetailAty onFailure===>" + t.getMessage());
                Toast.makeText(NewsDetailAty.this, "", Toast.LENGTH_SHORT).show();
                showDialog();
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

    private void showDialog() {
        final MaterialDialog dialog = new MaterialDialog.Builder(NewsDetailAty.this)
                .title("提示")
                .content("当前版本不支持此种类型的新闻，敬请期待！！也许是没网")
                .positiveText("我知道了")
                .cancelable(false)
                .build();
        dialog.show();

        dialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onBackPressed();
            }
        });
    }

    private SimpleDraweeView[] initImgs(final Context applicationContext) {
        SimpleDraweeView[] simpleDraweeViews = new SimpleDraweeView[imageViewCount];
        for (int i = 0; i < imageViewCount; i++) {
            final int index = i;
            DraweeController draweeController =
                    Fresco.newDraweeControllerBuilder()
                            .setUri(Uri.parse(imgs.get(i).src))
                            .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                            .build();
            SimpleDraweeView simpleDraweeView1 = new SimpleDraweeView(applicationContext);
            simpleDraweeView1.setImageURI(Uri.parse(imgs.get(i).src));
            simpleDraweeView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(applicationContext, ImageClickedAty.class);
                    intent.putExtra("imgUri", imgs.get(index).src);
                    intent.putExtra("HeadTitle", title);
                    startActivity(intent);
                }
            });
            simpleDraweeView1.setController(draweeController);
            simpleDraweeViews[i] = simpleDraweeView1;
        }
        return simpleDraweeViews;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLayoutBottom:
                Intent intent=new Intent(NewsDetailAty.this,ShowCommitsAty.class);
                intent.putExtra("replayBoard",replyBoard);
                intent.putExtra("postId",postId);
                intent.putExtra("replyCount",replyCount);
                startActivity(intent);
                break;
        }
    }
}
