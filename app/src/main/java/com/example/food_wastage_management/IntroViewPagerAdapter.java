package com.example.food_wastage_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

public class IntroViewPagerAdapter extends RecyclerView.Adapter<IntroViewPagerAdapter.ScreenViewHolder> {

    private Context mContext;
    private List<ScreenItem> mListScreen;

    public IntroViewPagerAdapter(Context context, List<ScreenItem> listScreen) {
        mContext = context;
        mListScreen = listScreen;
    }

    @NonNull
    @Override
    public ScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_screen, parent, false);
        return new ScreenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScreenViewHolder holder, int position) {
        holder.bindData(mListScreen.get(position));
    }

    @Override
    public int getItemCount() {
        return mListScreen.size();
    }

    class ScreenViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgSlide;
        private TextView title;
        private TextView description;

        ScreenViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSlide = itemView.findViewById(R.id.intro_img);
            title = itemView.findViewById(R.id.intro_title);
            description = itemView.findViewById(R.id.intro_desc);
        }

        void bindData(ScreenItem screenItem) {
            imgSlide.setImageResource(screenItem.getScreenImg());
            title.setText(screenItem.getTitle());
            description.setText(screenItem.getDescription());
        }
    }
}