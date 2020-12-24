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
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.R;

public class OnePlusNLayoutHelperAdapter extends DelegateAdapter.Adapter {
  private OnePlusNLayoutHelper onePlusNLayoutHelper;
  private Context context;

    public OnePlusNLayoutHelperAdapter(OnePlusNLayoutHelper onePlusNLayoutHelper, Context context) {
        this.onePlusNLayoutHelper = onePlusNLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new OnePlusNLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder v= (ViewHolder) holder;
        Glide.with(context).load("http://ww4.sinaimg.cn/large/610dc034jw1f2uyg3nvq7j20gy0p6myx.jpg").into(v.one);
    }

    @Override
    public int getItemCount() {

        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView one;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            one=itemView.findViewById(R.id.duo_image);
        }
    }
}
