package com.example.asus.newsdemo1.Http.Retrofit;

import com.example.asus.newsdemo1.Http.Retrofit.Service.NewsService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by codekk on 2016/4/3.
 * Email:  645326280@qq.com
 */
public class RetrofitClient {
    private static NewsService newsService;

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

}
