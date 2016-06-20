package com.fanhy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fanhy.imgloader.ImgLoader;
import com.fanhy.imgloader.R;

import java.net.URL;
import java.util.List;

/**
 * Created by huayong on 2016/7/17.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<String> list;
    private Context context;
    private LayoutInflater mInflater;
    private ImgLoader imgLoader;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        imgLoader = ImgLoader.getImgLoader();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_layout, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.iv);
        String url = list.get(position);
        imgLoader.loadImg(url, iv);
    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    View itemView;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }
}
