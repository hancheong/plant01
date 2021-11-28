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

public class store_GoodsAdapter extends RecyclerView.Adapter<store_GoodsAdapter.goodsViewHolder> {

    ArrayList<StoreGoods> arrayList;
    Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public store_GoodsAdapter(ArrayList<StoreGoods> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public store_GoodsAdapter.goodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.regoods, parent,false);
        return new goodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull goodsViewHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(arrayList.get(position).getStoreName())
//                .into(holder.iv_profile);
        holder.store1.setText(arrayList.get(position).getStoreName());
        holder.title1.setText(arrayList.get(position).getGoodsTitle());
        holder.review1.setText(arrayList.get(position).getGoodsReview());
        holder.price1.setText(arrayList.get(position).getGoodsPrice());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class goodsViewHolder extends RecyclerView.ViewHolder {
//        ImageView iv_profile;
        TextView store1;
        TextView title1;
        TextView review1;
        TextView price1;

        public goodsViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.store1 = itemView.findViewById(R.id.recommend_store1);
            this.title1 = itemView.findViewById(R.id.recommend_title1);
            this.review1 = itemView.findViewById(R.id.recommend_review1);
            this.price1 = itemView.findViewById(R.id.recommend_price1);
        }
    }
}