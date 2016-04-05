package com.example.asus.newsdemo1.Http.Retrofit.Service;


import com.example.asus.newsdemo1.Model.NewsDetail;
import com.example.asus.newsdemo1.Model.NewsSummary;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public interface NewsService {

    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Call<Map<String, List<NewsSummary>>> getNewsList(
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    //新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html

    @GET("nc/article/{postId}/full.html")
    Call<Map<String, NewsDetail>> getNewsDetail(@Path("postId") String postId);
}
