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
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.v_layout.R;

public class Adapter_StickyLayout extends DelegateAdapter.Adapter {
    private Context context;
    private StickyLayoutHelper stickyLayoutHelper;

    public Adapter_StickyLayout(Context context, StickyLayoutHelper stickyLayoutHelper) {
        this.context = context;
        this.stickyLayoutHelper = stickyLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new StickyLayoutHelper();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.float_item, parent, false);

        return new ViewHolder(view);
    }
    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener{
        void itemClick(int pos);
 }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder v= (ViewHolder) holder;
        Glide.with(context).load(R.drawable.xioawu).into(v.one);
        v.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.itemClick(position);
            }
        });
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
