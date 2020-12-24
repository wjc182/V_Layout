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
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.R;

public class GrildAdpter extends DelegateAdapter.Adapter{
   private GridLayoutHelper gridLayoutHelper;
   private Context context;

    public GrildAdpter(GridLayoutHelper gridLayoutHelper, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grild_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder v= (ViewHolder) holder;
        Glide.with(context).load(R.drawable.ic_launcher_background).into(v.grild);
        Glide.with(context).load(R.drawable.xioawu).into(v.aa);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView grild;
        private ImageView aa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            grild=itemView.findViewById(R.id.Grild_image);
            aa=itemView.findViewById(R.id.Grild_image2);
        }
    }
}
