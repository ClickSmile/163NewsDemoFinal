package com.example.asus.newsdemo1.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.asus.newsdemo1.Http.Retrofit.RetrofitClient;
import com.example.asus.newsdemo1.Model.Commits;
import com.example.asus.newsdemo1.Presenter.DividerItemDecoration;
import com.example.asus.newsdemo1.Presenter.RecyclerCommitAdapter;
import com.example.asus.newsdemo1.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by codekk on 2016/4/6.
 * Email:  645326280@qq.com
 */
public class ShowCommitsAty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerCommitAdapter adapter;

    private String replyBoard=null;
    private String postId=null;
    private int replyCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBarCommits);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        replyBoard=getIntent().getStringExtra("replayBoard");
        postId=getIntent().getStringExtra("postId");
        replyCount=Integer.parseInt(getIntent().getStringExtra("replyCount"));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCommitsShow);
        recyclerView.setNestedScrollingEnabled(false);
        int count=replyCount>20?20:replyCount;
        final Call<Commits> commits = RetrofitClient.getServiceCommits().getCommitsList(replyBoard, postId,count);
        commits.enqueue(new Callback<Commits>() {
            @Override
            public void onResponse(Call<Commits> call, Response<Commits> response) {
                Commits body = response.body();
                List<Commits.HotPost> posts = body.hotPosts;
                adapter = new RecyclerCommitAdapter(ShowCommitsAty.this, posts);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowCommitsAty.this));
                recyclerView.addItemDecoration(new DividerItemDecoration(ShowCommitsAty.this, DividerItemDecoration.VERTICAL_LIST));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Commits> call, Throwable t) {
                Log.d("KKLog", "ShowCommitsAty onFailure===>" + t.getMessage());
            }
        });
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

