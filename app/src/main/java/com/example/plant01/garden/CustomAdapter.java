//package com.example.plant01.garden;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.plant01.R;
//
//import java.util.ArrayList;
//
//public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {
//    private ArrayList<PlantsDB> arrayList;
//    private Context context;
//
//    public CustomAdapter(ArrayList<PlantsDB> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.garden_item,parent,false);
//        CustomHolder holder = new CustomHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(arrayList.get(position).getProfile())
//                .into(holder.iv_PlantsProfile);
//        holder.tv_Name.setText(arrayList.get(position).getName());
////        holder.tv_Location.setText(arrayList.get(position).getLocation());
////        holder.tv_Date.setText(String.valueOf(arrayList.get(position).getDate()));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return (arrayList != null ? arrayList.size() : 0);
//    }
//
//    public class CustomHolder extends RecyclerView.ViewHolder {
//        ImageView iv_PlantsProfile;
//        TextView tv_Name;
////        TextView tv_Location;
////        TextView tv_Date;
//
//        public CustomHolder(@NonNull View itemView) {
//            super(itemView);
//            this.iv_PlantsProfile = itemView.findViewById(R.id.iv_PlantsProfile);
//            this.tv_Name = itemView.findViewById(R.id.tv_Name);
////            this.tv_Location = itemView.findViewById(R.id.tv_Location);
////            this.tv_Date = itemView.findViewById(R.id.tv_Date);
//        }
//    }
//}
