package com.example.plant01.adaptor;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;
import com.example.plant01.home.home_Plants;
import com.example.plant01.store.store_SearchResult;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class home_PlantAdapter extends RecyclerView.Adapter<home_PlantAdapter.PlantViewHolder> {

    Context context;
    ArrayList<home_Plants> homePlantsArrayList;

    public home_PlantAdapter(Context context, ArrayList<home_Plants> homePlantsArrayList) {
        this.context = context;
        this.homePlantsArrayList = homePlantsArrayList;
    }



    @NonNull
    @Override
    public home_PlantAdapter.PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view  = LayoutInflater.from(context).inflate(R.layout.home_item, parent,false);

        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull home_PlantAdapter.PlantViewHolder holder, int position) {


        holder.plantName.setText(homePlantsArrayList.get(position).getPlantName());
        Glide.with(holder.plantImg)
                .load(homePlantsArrayList.get(position).getPlantImg())
                .into(holder.plantImg);

    }

    @Override
    public int getItemCount() {
        return homePlantsArrayList.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView plantImg;
        TextView plantName;
        public PlantViewHolder(@NonNull View itemView) {
            super(itemView);
            plantImg = itemView.findViewById(R.id.iv_plantImg);
            plantName = itemView.findViewById(R.id.tv_plantName);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int currentPos = getAdapterPosition();
                    home_Plants homePlants = homePlantsArrayList.get(currentPos);
                    String plantname = homePlants.getPlantName();
                    Intent intent = new Intent(context, store_SearchResult.class);
                    intent.putExtra("contact_search",plantname);
                    context.startActivity(intent);
                    Log.e("plantname", homePlants.getPlantName());

                }
            });

        }
    }
}
