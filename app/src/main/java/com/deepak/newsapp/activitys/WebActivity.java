package com.deepak.newsapp.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.deepak.newsapp.R;

public class WebActivity extends AppCompatActivity {
    private WebView web_view_news;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web_view_news = findViewById(R.id.web_view_news);

        Intent intent = getIntent();
        if (null != intent) {
            String urlIs = intent.getStringExtra("url");
            if (null != urlIs) {
                web_view_news.setWebViewClient(new WebViewClient());
                web_view_news.getSettings().setJavaScriptEnabled(true);
                web_view_news.loadUrl(urlIs);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (web_view_news.canGoBack()) {
            getObbDir();
        } else {
            super.onBackPressed();
        }

    }
}