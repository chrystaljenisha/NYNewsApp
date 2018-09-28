package com.example.hp_pc.newsapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_pc.newsapp.Model.Article;
import com.example.hp_pc.newsapp.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Article> articleList;
    private LayoutInflater inflater;
    private Context mContext;
    private WebView webview;

    private WebViewListner mListener;

    public interface WebViewListner {
        void onClickCard(String url);
    }

    public RecyclerViewAdapter(Context  context,List<Article> articleList, WebViewListner listener) {
        inflater = LayoutInflater.from(context);
        this.articleList = articleList;
        this.mListener=listener;
        mContext=context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.article_cardview,viewGroup,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        final Article article=articleList.get(position);
        myViewHolder.setData(article,position);
        myViewHolder.viewHolderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickCard(article.getUrl());

            }
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title,writers,content,publishedDate;
        private Article article;
        public View viewHolderView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewHolderView=itemView;
            title= (TextView) itemView.findViewById(R.id.title);
            writers= (TextView) itemView.findViewById(R.id.writers);
            content= (TextView) itemView.findViewById(R.id.content);
            publishedDate=(TextView) itemView.findViewById(R.id.publishedDate);
        }

        public void setData(Article article, int position) {
            this.title.setText(article.getTitle());
            this.writers.setText(article.getByLine());
            this.publishedDate.setText("Published On: "+article.getDate());
            this.content.setText("Section: "+article.getSection());
            this.article=article;

        }
    }


}
