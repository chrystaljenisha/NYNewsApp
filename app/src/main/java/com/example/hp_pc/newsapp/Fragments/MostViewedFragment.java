package com.example.hp_pc.newsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.hp_pc.newsapp.Adapter.RecyclerViewAdapter;
import com.example.hp_pc.newsapp.Model.Article;
import com.example.hp_pc.newsapp.Utils.FetchDataAsyncTask;
import com.example.hp_pc.newsapp.R;
import com.example.hp_pc.newsapp.Utils.NetworkMonitor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MostViewedFragment extends Fragment implements RecyclerViewAdapter.WebViewListner {

    View view;
    String result;
    FetchDataAsyncTask asyncTask;
    RecyclerView articleListView;
    ProgressBar progress;
    WebView webview;

    public MostViewedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mostviewed,container,false);
        articleListView = view.findViewById(R.id.mostViewedList);
        progress = view.findViewById(R.id.progressBarFetch);
        webview = view.findViewById(R.id.webView);
        webview.setVisibility(View.GONE);
        if(NetworkMonitor.isNetworkOnline(getActivity())) {
            asyncTask = new FetchDataAsyncTask(this);
            asyncTask.execute();
        }else{
            Toast.makeText(getActivity(),"Please connect to Internet",Toast.LENGTH_LONG).show();
            }
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(isTaskRunning(asyncTask)) {
           showProgressBar();
        }else {
            hideProgressBar();
       }
        if(result!=null) {
            try {
                populateResult(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        super.onActivityCreated(savedInstanceState);
    }

    public void showProgressBar() {
        //articleListView.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
        progress.setIndeterminate(true);
   }

     public void hideProgressBar() {
         //articleListView.setVisibility(View.VISIBLE);
       progress.setVisibility(View.GONE);
    }

    public void populateResult(String result) throws JSONException {
        Gson gson = new GsonBuilder().create();
        JSONObject obj = new JSONObject(result);
        JSONArray jsonArray=obj.getJSONArray("results");
        Type listType = new TypeToken<List<Article>>(){}.getType();
        List<Article> articles = gson.fromJson(jsonArray.toString(), listType);
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(getActivity(),articles,this);
        articleListView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        articleListView.setLayoutManager(layoutManager);
    }

    protected boolean isTaskRunning(FetchDataAsyncTask task) {
        if(task==null ) {
            return false;
        } else if(task.getStatus() == FetchDataAsyncTask.Status.FINISHED){
           return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClickCard(String url) {
        webview.setVisibility(View.VISIBLE);
        articleListView.setVisibility(View.GONE);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }

    public boolean canGoBack() {
        return webview.canGoBack();
    }

    public boolean goBack() {
        if(webview.getVisibility()==View.VISIBLE) {
            webview.loadUrl("about:blank");
            webview.setVisibility(View.GONE);
            articleListView.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
}



