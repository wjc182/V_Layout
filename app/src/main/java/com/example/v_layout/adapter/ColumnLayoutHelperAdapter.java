package com.example.v_layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.Bean;
import com.example.v_layout.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class ColumnLayoutHelperAdapter extends DelegateAdapter.Adapter {
   private ColumnLayoutHelper columnLayoutHelper;
   private Context context;

    public ColumnLayoutHelperAdapter(ColumnLayoutHelper columnLayoutHelper, Context context) {
        this.columnLayoutHelper = columnLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return columnLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ban_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder v= (ViewHolder) holder;
        Glide.with(context).load("http://ww4.sinaimg.cn/large/610dc034jw1f2uyg3nvq7j20gy0p6myx.jpg").into(v.ban);
        Glide.with(context).load("http://ww4.sinaimg.cn/large/610dc034jw1f2uyg3nvq7j20gy0p6myx.jpg").into(v.ban2);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         private ImageView ban;
         private ImageView ban2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ban=itemView.findViewById(R.id.ban_image);
            ban2=itemView.findViewById(R.id.ban_image2);
        }
    }
}
