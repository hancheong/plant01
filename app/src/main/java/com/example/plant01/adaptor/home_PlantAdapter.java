package com.example.plant01.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.example.plant01.home.HomeFragment;
import com.example.plant01.home.Plant;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class home_PlantAdapter extends RecyclerView.Adapter<home_PlantAdapter.PlantViewHolder> {

    Context context;
    ArrayList<Plant> plantArrayList;

    public home_PlantAdapter(Context context, ArrayList<Plant> plantArrayList) {
        this.context = context;
        this.plantArrayList = plantArrayList;
    }



    @NonNull
    @Override
    public home_PlantAdapter.PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(context).inflate(R.layout.home_item, parent,false);

        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull home_PlantAdapter.PlantViewHolder holder, int position) {

        Plant plant = plantArrayList.get(position);

        holder.plantName.setText(plant.getPlantName());
        Glide.with(holder.plantImg)
                .load(plantArrayList.get(position).getPlantImg())
                .into(holder.plantImg);

    }

    @Override
    public int getItemCount() {
        return plantArrayList.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView plantImg;
        TextView plantName;
        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            plantImg = itemView.findViewById(R.id.iv_plantImg);
            plantName = itemView.findViewById(R.id.tv_plantName);

        }
    }
}
