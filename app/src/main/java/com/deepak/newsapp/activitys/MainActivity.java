package com.deepak.newsapp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.deepak.newsapp.R;
import com.deepak.newsapp.adapter.NewsAdapter;
import com.deepak.newsapp.api.call.NewsInfo;
import com.deepak.newsapp.api.call.RetroInformation;

import com.deepak.newsapp.headlines.Article;
import com.deepak.newsapp.headlines.NewsReport;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;

    public static final String API_KEY = "f5f98e98f1e64d5e80f2a8b449dd8832";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = this.findViewById(R.id.FirstRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final NewsInfo newsInfo = RetroInformation.getClient().create(NewsInfo.class);
        Call<NewsReport> call = newsInfo.getInfo("us", API_KEY);
        call.enqueue(new Callback<NewsReport>() {
            @Override
            public void onResponse(Call<NewsReport> call, Response<NewsReport> response) {
                assert response.body() != null;
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                } else {

                    articles = response.body().getArticles();
                    adapter = new NewsAdapter(articles, MainActivity.this);

                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsReport> call, Throwable t) {
                Log.e("out", t.toString());
            }
        });


    }

}