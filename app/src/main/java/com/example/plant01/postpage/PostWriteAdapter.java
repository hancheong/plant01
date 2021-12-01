package com.example.plant01.postpage;

import static com.example.plant01.usersetting.Util.INTENT_PATH;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.plant01.R;

import java.util.ArrayList;

public class PostWriteAdapter extends RecyclerView.Adapter<PostWriteAdapter.PostViewHolder> {
    private ArrayList<Writeinfo> mDataset;
    private Activity activity;

    static class PostViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        PostViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public PostWriteAdapter(Activity activity, ArrayList<Writeinfo> myDataset) {
        mDataset = myDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_write, parent, false);
        final PostViewHolder galleryViewHolder = new PostViewHolder(cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return galleryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textView = cardView.findViewById(R.id.titleTextView);
        textView.setText(mDataset.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

//package com.example.plant01.postpage;
//
//import android.app.Activity;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.plant01.R;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class PostWriteAdapter extends RecyclerView.Adapter<PostWriteAdapter.PostViewHolder> {
//private ArrayList<Writeinfo> mDataset;
//private Activity activity;
//
//static class PostViewHolder extends RecyclerView.ViewHolder {
//    CardView cardView;
//    PostViewHolder(CardView v) {
//        super(v);
//        cardView = v;
//    }
//}
//
//    public PostWriteAdapter(Activity activity, ArrayList<Writeinfo> myDataset) {
//        mDataset = myDataset;
//        this.activity = activity;
//    }
//
//    @NonNull
//    @Override
//    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_write, parent, false);
//        final PostViewHolder galleryViewHolder = new PostViewHolder(cardView);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
//
//        return galleryViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
//        CardView cardView = holder.cardView;
//        TextView titleTextView = cardView.findViewById(R.id.titleTextView);
//        titleTextView.setText(mDataset.get(position).getTitle());
//
//        TextView createdAtTextView = cardView.findViewById(R.id.createdAtTextView);
//        createdAtTextView.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(mDataset.get(position).getDate()));
//
//        LinearLayout contentsLayout = cardView.findViewById(R.id.contentsLayout);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ArrayList<String> contentList = mDataset.get(position).getContents();
//
//        if(contentsLayout.getChildCount() == 0) {
//            for (int i = 0; i < contentList.size(); i++) {
//                String contents = contentList.get(i);
//                if (Patterns.WEB_URL.matcher(contents).matches()) {
//                    ImageView imageView = new ImageView(activity);
//                    imageView.setLayoutParams(layoutParams);
//                    imageView.setAdjustViewBounds(true);
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                    contentsLayout.addView(imageView);
//                    Glide.with(activity).load(contents).override(1000).thumbnail(0.1f).into(imageView);
//                } else {
//                    TextView textView = new TextView(activity);
//                    textView.setLayoutParams(layoutParams);
//                    textView.setText(contents);
//                    contentsLayout.addView(textView);
//                }
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataset.size();
//    }
//}