package com.example.asus.newsdemo1.Http.Retrofit.Service;

import com.example.asus.newsdemo1.Model.Commits;
import com.example.asus.newsdemo1.Model.NewsSummary;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by codekk on 2016/4/6.
 * Email:  645326280@qq.com
 */
public interface CommitsService {

    //eg: http://comment.api.163.com/api/json/post/list/new/hot/ent2_bbs/BJVUPHNI00031H2L/0/5/10/2/2
    //type   ====>   replyBoard
    //id     =====>  postId
    //endPage =====> 一次显示多少页
    @GET("api/json/post/list/new/hot/{type}/{id}/0/{endPage}/10/2/2")
    Call<Commits> getCommitsList(
            @Path("type") String type, @Path("id") String id,
            @Path("endPage") int endPage);

}
