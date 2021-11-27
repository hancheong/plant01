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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    Context context;
    ArrayList<PostItem> postArrayList;

    public PostAdapter(Context context, ArrayList<PostItem> postArrayList) {
        this.context = context;
        this.postArrayList = postArrayList;
    }



    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.post_list_item, parent,false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {



        holder.usernick.setText(postArrayList.get(position).getUserNick());
        holder.postdate.setText((CharSequence) postArrayList.get(position).getDate());
        holder.likenum.setText(postArrayList.get(position).getLike());
        holder.content.setText(postArrayList.get(position).getContent());
        Glide.with(holder.postimg)
                .load(postArrayList.get(position).getContentImg())
                .into(holder.postimg);
        Glide.with(holder.userimg)
                .load(postArrayList.get(position).getUserImg())
                .into(holder.userimg);

    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView userimg, postimg;
        TextView usernick, postdate, likenum, content;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            userimg = itemView.findViewById(R.id.user_img);
            usernick = itemView.findViewById(R.id.userid);
            postimg = itemView.findViewById(R.id.post_img);
            postdate = itemView.findViewById(R.id.post_date);
            likenum = itemView.findViewById(R.id.like_num);
            content = itemView.findViewById(R.id.explain);

        }
    }
}
