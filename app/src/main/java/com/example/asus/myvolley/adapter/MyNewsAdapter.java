package com.example.asus.myvolley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.myvolley.R;
import com.example.asus.myvolley.bean.New;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Asus on 2016/5/5.
 */
public class MyNewsAdapter extends BaseAdapter {
    private List<New> list;
    private Context mContext;

    public MyNewsAdapter(List<New> list, Context mContext) {
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
        if (convertView==null){
            viewHodler=new ViewHodler();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.new_item,null);
            viewHodler.title=(TextView)convertView.findViewById(R.id.news_title);
            viewHodler.content=(TextView)convertView.findViewById(R.id.news_context);
            viewHodler.imageView=(ImageView)convertView.findViewById(R.id.news_image);
            convertView.setTag(viewHodler);

        }else{
            viewHodler=(ViewHodler)convertView.getTag();
        }
        viewHodler.title.setText(list.get(position).getTitle());
        viewHodler.content.setText(list.get(position).getContent());
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),viewHodler.imageView);
        return convertView;
    }
    class ViewHodler{
        private ImageView imageView;
        private TextView title,content;
    }
}
