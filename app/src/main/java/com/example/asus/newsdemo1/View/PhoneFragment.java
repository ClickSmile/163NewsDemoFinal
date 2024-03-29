package com.example.asus.newsdemo1.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.asus.newsdemo1.Http.Retrofit.RetrofitClient;
import com.example.asus.newsdemo1.Model.NewsSummary;
import com.example.asus.newsdemo1.Presenter.DividerItemDecoration;
import com.example.asus.newsdemo1.Presenter.RecyclerHeadlineAdapter;
import com.example.asus.newsdemo1.R;
import com.example.asus.newsdemo1.Utils.SimpleUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by codekk on 2016/4/6.
 * Email:  645326280@qq.com
 */
public class PhoneFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ConvenientBanner<String> convenientBanner;
    private RecyclerHeadlineAdapter adapter;
    private RecyclerView recyclerView;
    private List<NewsSummary> newsVerticalList;   //首页新闻的垂直方向上的列表内容
    List<NewsSummary.AdsEntity> newsHeadList;    //首页新闻水平轮换图
    private List<String> simpleDraweeViews;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_headline,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHeadlineContent);
        convenientBanner = (ConvenientBanner<String>) view.findViewById(R.id.convenientBannerHeadlineContent);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshHeadline);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        convenientBanner.setVisibility(View.GONE);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        simpleDraweeViews = new ArrayList<>();
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        News1();
    }

    private void News1() {
        Call<Map<String, List<NewsSummary>>> newsList = RetrofitClient.getService().getNewsList("list", "T1348649654285", 0);
        newsList.enqueue(new Callback<Map<String, List<NewsSummary>>>() {
            @Override
            public void onResponse(Call<Map<String, List<NewsSummary>>> call, Response<Map<String, List<NewsSummary>>> response) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                }
                newsHeadList = new ArrayList<NewsSummary.AdsEntity>();
                Map<String, List<NewsSummary>> body = response.body();
                for (Map.Entry<String, List<NewsSummary>> entry : body.entrySet()) {
                    List<NewsSummary> value = entry.getValue();
                    newsVerticalList = SimpleUtils.makeNewsList(value);
                    NewsSummary newsSummary = value.get(0);
                    newsHeadList=newsSummary.ads;
                    if(newsSummary.imgextra==null || newsSummary.imgextra.isEmpty()){
                        Log.d("KKLog", "News2 newsSummary.imgextra is null or empty!!");
                    }

                }
                adapter = new RecyclerHeadlineAdapter(getContext(), newsVerticalList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Map<String, List<NewsSummary>>> call, Throwable t) {
                Log.d("KKLog", "News2 onFailure "+t.getMessage());
            }
        });
    }

    @Override
    public void onRefresh() {
        simpleDraweeViews.clear();
        News1();
    }
}
