package com.example.plant01.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plant01.R;

import java.util.ArrayList;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.TipViewHolder> {


    ArrayList<String> tipArrayList = null;

    public searchAdapter(ArrayList<String> tipArrayList) {
        this.tipArrayList = tipArrayList;
    }



    @NonNull
    @Override
    public searchAdapter.TipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.result_tip_item,viewGroup, false) ;
        searchAdapter.TipViewHolder vh = new searchAdapter.TipViewHolder(view) ;


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull searchAdapter.TipViewHolder tipViewHolder, int position) {
        String text = tipArrayList.get(position) ;
        tipViewHolder.tip.setText(text) ;
//        tipViewHolder.tip.setText(tipArrayList.get(position).getTip());
    }

    @Override
    public int getItemCount() {
        return tipArrayList.size();
    }

    public class TipViewHolder extends RecyclerView.ViewHolder {
        TextView tip;
        public TipViewHolder(@NonNull View itemView) {
            super(itemView);
            tip = itemView.findViewById(R.id.tip);
        }
    }
//
//    private class Tip {
//        String tip;
//
//        public Tip(String tip) {
//            this.tip = tip;
//        }
//
//        public String getTip() {
//            return tip;
//        }
//
//        public void setTip(String tip) {
//            this.tip = tip;
//        }
//    }
}
