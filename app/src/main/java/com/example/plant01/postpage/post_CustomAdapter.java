package com.example.plant01.postpage;

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

public class post_CustomAdapter extends RecyclerView.Adapter<post_CustomAdapter.CustomViewHolder> {

    private ArrayList<Post> arrayList;
    private Context context;

    public post_CustomAdapter(ArrayList<Post> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public post_CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull post_CustomAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.user_img);
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.post_img);
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.post_like);
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.comment);
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.share);
        holder.userid.setText(arrayList.get(position).getUsername());
        holder.postdate.setText(String.valueOf(arrayList.get(position).getDate()));
        holder.explain.setText(arrayList.get(position).getContent());
        holder.like_num.setText(arrayList.get(position).getLike_num());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView user_img;
        ImageView post_img;
        ImageView post_like;
        ImageView comment;
        ImageView share;
        TextView userid;
        TextView postdate;
        TextView explain;
        TextView like_num;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.user_img = itemView.findViewById(R.id.user_img);
            this.post_img = itemView.findViewById(R.id.post_img);
            this.post_like = itemView.findViewById(R.id.post_like);
            this.comment = itemView.findViewById(R.id.comment);
            this.share = itemView.findViewById(R.id.share);
            this.userid = itemView.findViewById(R.id.userid);
            this.postdate = itemView.findViewById(R.id.post_date);
            this.explain = itemView.findViewById(R.id.explain);
            this.like_num = itemView.findViewById(R.id.like_num);
        }
    }
}
