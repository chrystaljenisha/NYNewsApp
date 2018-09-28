package com.example.hp_pc.newsapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.hp_pc.newsapp.Fragments.MostViewedFragment;
import com.example.hp_pc.newsapp.Utils.Constants;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataAsyncTask extends AsyncTask<String, String, String> {

    MostViewedFragment container;

    public FetchDataAsyncTask(MostViewedFragment fragment) {
        container = fragment;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        container.showProgressBar();
    }

    protected String doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(Constants.URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);

            }

            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (container != null && container.getActivity() != null) {
            try {
                container.populateResult(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            container.hideProgressBar();
            this.container = null;
        }

    }
}

