package com.deepak.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deepak.newsapp.R;
import com.deepak.newsapp.activitys.WebActivity;
import com.deepak.newsapp.headlines.Article;
import com.deepak.newsapp.headlines.TimeFormate;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Article> articles;
    private Context context;

    public NewsAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.news_title.setText(article.getTitle());
        Glide.with(context).asBitmap()
                .load(article.getUrlToImage())
                .into(holder.news_image);
        holder.news_description.setText(article.getDescription());
        holder.article_author.setText(article.getAuthor());
        holder.news_published_date.setText(TimeFormate.currentTimeIs(article.getPublishedAt()));

        holder.parent.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("url", articles.get(position).getUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    static class NewsViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView parent;
        ImageView news_image;
        TextView news_title, article_author, news_description, news_published_date;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            news_image = itemView.findViewById(R.id.news_image);
            news_title = itemView.findViewById(R.id.news_title);
            article_author = itemView.findViewById(R.id.article_author);
            news_description = itemView.findViewById(R.id.news_description);
            news_published_date = itemView.findViewById(R.id.news_published_date);
        }
    }
}
