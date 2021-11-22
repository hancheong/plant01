package com.example.plant01.postpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class post_FragmentAdapter extends FragmentStateAdapter {
//    public post_FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }

    public post_FragmentAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new Post_diary();
            case 1:
                return new Post_best();
            case 2:
                return new Post_free();
            case 3:
                return new Post_info();
//            default:
//                return null;
        }

        return new Post_diary();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}