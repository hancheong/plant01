package com.example.plant01.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;

import java.util.ArrayList;
import java.util.List;

public class store_GoodsAdapter extends RecyclerView.Adapter<store_GoodsAdapter.store_viewholder> {

    private Store_RecommendList activity;
    private List<StoreGoods> goodsList;

    public store_GoodsAdapter(Store_RecommendList activity, List<StoreGoods> goodsList){
        this.activity = activity;
        this.goodsList = goodsList;
    }

    @NonNull
    @Override
    public store_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.regoods, parent,false);
        return new store_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull store_viewholder holder, int position) {
        holder.store1.setText(goodsList.get(position).getStoreName());
        holder.title1.setText(goodsList.get(position).getGoodsTitle());
        holder.review1.setText(goodsList.get(position).getGoodsReview());
        holder.price1.setText(goodsList.get(position).getGoodsPrice());
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public static class store_viewholder extends RecyclerView.ViewHolder{

        TextView store1, title1, review1, price1;

        public store_viewholder(@NonNull View itemView) {
            super(itemView);

            store1 = itemView.findViewById(R.id.recommend_store1);
            title1 = itemView.findViewById(R.id.recommend_title1);
            review1 = itemView.findViewById(R.id.recommend_review1);
            price1 = itemView.findViewById(R.id.recommend_price1);
        }
    }
}