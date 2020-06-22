package com.security10x.socialdistancing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.security10x.socialdistancing.Adapter.FeedAdapter;
import com.security10x.socialdistancing.Common.HTTPDataHandler;
import com.security10x.socialdistancing.Model.RSSObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class QuoteActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS link
    private final String RSS_link="https://www.brainyquote.com/link/quotebr.rss";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        new InternetCheck(new InternetCheck.Consumer() {
            @Override
            public void accept(Boolean internet) {
                if (!internet) {
                    Toast.makeText(getApplicationContext(), "No Internet Connection. Please connect to the internet.", Toast.LENGTH_SHORT).show();
                }else{
                    toolbar = (Toolbar) findViewById(R.id.toolbar);
                    toolbar.setTitle("Quotes Of The Day");
                    setSupportActionBar(toolbar);
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    loadRSS();
                }
            }
        });
    }

    private void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(QuoteActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Please wait...");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return  result;
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_link);
        loadRSSAsync.execute(url_get_data.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh)
            loadRSS();
        return true;
    }
}