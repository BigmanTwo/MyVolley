package com.example.asus.myvolley;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myvolley.adapter.MyNewsAdapter;
import com.example.asus.myvolley.bean.New;
import com.example.asus.myvolley.bean.News;
import com.example.asus.myvolley.bean.NewsTotal;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private Context mContext;
    private String url;
    private News news;
    private List<New> list;
    private ListView mListView;
    private RequestQueue mQueue;
    public static final String URLS="http://op.juhe.cn/onebox/news/query?key=b1cccdf6af5cd58b7ed712a9ccb3af46&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mListView=(ListView)findViewById(R.id.new_list);
        mContext=this;
        url=getIntent().getStringExtra("String");
        Log.d("url",url);
        url=URLS+url;
        getDate();
    }
    private void getDate(){
        mQueue= Volley.newRequestQueue(this);
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url , null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("news",String.valueOf(response));
                        NewsTotal newsTotal= JSON.parseObject(String.valueOf(response),NewsTotal.class);
                        Log.d("news",newsTotal.toString());
                         list=newsTotal.getResult();

                        MyNewsAdapter adapter=new MyNewsAdapter(list,mContext);
                        mListView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }
}
