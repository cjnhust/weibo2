package com.bathust.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bathust.R;

import java.util.List;
import java.util.Map;

/**
 * Created by bathust on 15-4-14.
 */
public class WeiBoAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;
    public WeiBoAdapter(Context context, List<Map<String,Object>> list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        final ViewHolder viewHolder;
        if(convertView==null) {
            view  = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();

            viewHolder.userPhoto = (ImageView) view.findViewById(R.id.user_photo);
            viewHolder.userName= (TextView) view.findViewById(R.id.user_name);
            viewHolder.userWeiBo = (TextView) view.findViewById(R.id.user_weibo);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
<<<<<<< HEAD
        if(position<list.size()-1) {
=======
        if(position<list.size()) {
>>>>>>> 8cebb8cfd8e0a3cb1955e9ab67245e56835092d6
            viewHolder.userName.setText((String) list.get(position).get("name"));
            viewHolder.userWeiBo.setText((String) list.get(position).get("text"));
        }
        return view;

    }
    class ViewHolder{
        TextView userName,userWeiBo;
        ImageView userPhoto;
    }
}
