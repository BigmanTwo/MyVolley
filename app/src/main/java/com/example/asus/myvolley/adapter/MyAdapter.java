package com.example.asus.myvolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.myvolley.R;

import java.util.List;

/**
 * Created by Asus on 2016/5/5.
 */
public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private Context mContext;

    public MyAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler=null;
        if(convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            viewHodler.textView=(TextView)convertView.findViewById(R.id.item);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        String str=list.get(position);
        viewHodler.textView.setText(str);
        return convertView;
    }
    class ViewHodler{
        private TextView textView;
    }
}
