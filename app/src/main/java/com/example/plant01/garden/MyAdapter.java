package com.example.plant01.garden;

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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Model> mList;

    public MyAdapter(Context context, List<Model> mList){
        this.context = context;
        this.mList = mList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.testitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getProfileUri()).into(holder.profile);
        holder.name.setText(mList.get(position).getName());
        holder.location.setText(mList.get(position).getLocation());
        holder.date.setText(mList.get(position).getDate());


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, location, date;
        ImageView profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profile = itemView.findViewById(R.id.iv_PlantsProfile);
            name = itemView.findViewById(R.id.name_text);
            location = itemView.findViewById(R.id.location_text);
            date = itemView.findViewById(R.id.date_text);
        }
    }
}
