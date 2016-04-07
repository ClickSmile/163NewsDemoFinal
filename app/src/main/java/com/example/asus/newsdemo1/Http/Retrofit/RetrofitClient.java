package com.example.asus.newsdemo1.Http.Retrofit;

import com.example.asus.newsdemo1.Http.Retrofit.Service.CommitsService;
import com.example.asus.newsdemo1.Http.Retrofit.Service.NewsService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public class RetrofitClient {
    private static NewsService newsService;
    private static CommitsService commitsService;

    public static NewsService getService(){
        if(null==newsService){
            newsService= new Retrofit.Builder()
                    .baseUrl(Api.NETEAST_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewsService.class);
        }
        return newsService;
    }

    public static CommitsService getServiceCommits(){
        if(null==commitsService){
            commitsService= new Retrofit.Builder()
                    .baseUrl(Api.COMMITS_BASE_URI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CommitsService.class);
        }
        return commitsService;
    }

}
