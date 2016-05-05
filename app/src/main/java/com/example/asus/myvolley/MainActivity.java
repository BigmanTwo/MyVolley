package com.example.asus.myvolley;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.myvolley.adapter.MyAdapter;
import com.example.asus.myvolley.bean.News;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<String> list;
    private Context mContext;
    private RequestQueue mQueue;
    private ListView mListView;
    public static final String URLS="http://op.juhe.cn/onebox/news/words?key=b1cccdf6af5cd58b7ed712a9ccb3af46";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mListView=(ListView)findViewById(R.id.list);
        getDate();
        mListView.setOnItemClickListener(this);
//        MyThread myThread=new MyThread();
//        myThread.start();
    }
    private void getDate(){
        mQueue= Volley.newRequestQueue(mContext);
         JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URLS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    News news= JSON.parseObject(String.valueOf(response),News.class);
                        Log.d("news",news.toString());
                         list=news.getResult();
                        MyAdapter adapter=new MyAdapter(list,mContext);
                        mListView.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String string=list.get(position);
        Intent intent=new Intent(mContext,NewsActivity.class);
        intent.putExtra("String",string);
        startActivity(intent);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            try {
                URL url=new URL(URLS);
                URLConnection connection=url.openConnection();
                HttpURLConnection httpURLConnection= (HttpURLConnection) connection;
                httpURLConnection.setRequestMethod("GET");//设置网络请求的一个方式
                httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");//编码格式
                int code=httpURLConnection.getResponseCode();//获得网络连接的返回值
                if(code==HttpURLConnection.HTTP_OK){
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                    String str=reader.readLine();
                    while(reader.readLine()!=null){
                        str=str+reader.readLine();
                    }
                    Log.d("返回值",str);
                    Gson gson=new Gson();
                    News news=gson.fromJson(str,News.class);
                    List<String> list=news.getResult();
                    Message msg=mHandler.obtainMessage();
                    msg.obj=list;
                    msg.what=1;
                    mHandler.sendMessage(msg);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                List<String> list= (List<String>) msg.obj;
                if(list!=null){
                    MyAdapter adpter=new MyAdapter(list,mContext);
                    mListView.setAdapter(adpter);
                }
            }
        }
    };
}
