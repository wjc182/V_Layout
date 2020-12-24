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
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.R;

public class ScrollFixLayoutHelperAdafel extends DelegateAdapter.Adapter {
    private Context context;
    private ScrollFixLayoutHelper scrollFixLayoutHelper;

    public ScrollFixLayoutHelperAdafel(Context context, ScrollFixLayoutHelper scrollFixLayoutHelper) {
        this.context = context;
        this.scrollFixLayoutHelper = scrollFixLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.float_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        Glide.with(context).load(context).into(viewHolder.one);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView one;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            one=itemView.findViewById(R.id.flot_image);
        }
    }
}
