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
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.Bean;
import com.example.v_layout.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class OnePlusNLayoutHelperAdapter2 extends DelegateAdapter.Adapter {
    private ColumnLayoutHelper columnLayoutHelper;
  private Context context;
  private ArrayList<Bean.DataDTO> bean;

    public OnePlusNLayoutHelperAdapter2(ColumnLayoutHelper columnLayoutHelper, Context context, ArrayList<Bean.DataDTO> bean) {
        this.columnLayoutHelper = columnLayoutHelper;
        this.context = context;
        this.bean = bean;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new OnePlusNLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ban_item1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ViewHolder v= (ViewHolder) holder;
        v.one.setImages(bean)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Bean.DataDTO poi= (Bean.DataDTO) path;
                        Glide.with(context).load(poi.getImagePath()).into(imageView);
                    }
                }).start();
    }

    @Override
    public int getItemCount() {

        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private Banner one;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            one=itemView.findViewById(R.id.banner_ban);
        }
    }
}
