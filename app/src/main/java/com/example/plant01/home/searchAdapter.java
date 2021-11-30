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

    Context context;
    ArrayList<String> tipArrayList;

    public searchAdapter(Context context, ArrayList<String> tipArrayList) {
        this.context = context;
        this.tipArrayList = tipArrayList;
    }



    @NonNull
    @Override
    public searchAdapter.TipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.result_tip_item, viewGroup,false);

        return new TipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchAdapter.TipViewHolder tipViewHolder, int position) {
        String text = tipArrayList.get(position) ;
        tipViewHolder.textView1.setText(text) ;
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

    private class Tip {
        String tip;

        public Tip(String tip) {
            this.tip = tip;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
