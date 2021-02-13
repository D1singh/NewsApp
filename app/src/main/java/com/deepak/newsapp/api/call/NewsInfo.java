package com.deepak.newsapp.api.call;

import com.deepak.newsapp.headlines.NewsReport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsInfo {
@GET("top-headlines")
    Call<NewsReport> getInfo(
            @Query("country")String country,
            @Query("apiKey") String key);

}
