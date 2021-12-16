package com.example.plant01.adaptor;

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

import com.example.plant01.home.Users;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Users> userArrayList;

//    public MyAdapter(Context context, ArrayList<Users> userArrayList) {
//
//    }

    public MyAdapter(Context context, ArrayList<Users> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.home_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //holder에서 준 변수명, User에서 준 변수명
        //
        Users user = userArrayList.get(position);

        holder.firstName.setText(user.firstName);
//        holder.lastName.setText(user.lastName);
//        holder.Age.setText(String.valueOf(user.age));
        Glide.with(holder.Image)
                .load(userArrayList.get(position).getUserImg())
                .into(holder.Image);



    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, lastName, Age;
        ImageView Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.tv_plantName);
            Image = itemView.findViewById(R.id.iv_plantImg);
        }
    }
}
