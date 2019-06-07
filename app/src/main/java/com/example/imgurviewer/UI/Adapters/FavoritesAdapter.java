package com.example.imgurviewer.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imgurviewer.Models.Api.FavImage;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.R;
import com.example.imgurviewer.UI.Activities.FavoritesActivity;
import com.example.imgurviewer.UI.Activities.ImageDetailActivity;
import com.example.imgurviewer.UI.Activities.ImageDetailFragment;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<FavImage> images;
    private final FavoritesActivity activity;
    private final boolean mTwoPane;

    private final View.OnClickListener favItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FavImage favImage = (FavImage)v.getTag();
            if (mTwoPane){
                Bundle arguments = new Bundle();
                arguments.putParcelable(ImageDetailFragment.ARG_ITEM_ID, favImage);
                ImageDetailFragment fragment = new ImageDetailFragment();
                fragment.setArguments(arguments);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.favorite_detail_container, fragment)
                        .commit();
            }
            else{
                Context context = v.getContext();
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra(FavoritesActivity.FAVORITE_DETAILS, favImage);
                context.startActivity(intent);
            }
        }
    };

    public FavoritesAdapter(FavoritesActivity parent,
                            List<FavImage> imageList,
                            boolean twoPane){
        activity = parent;
        images = imageList;
        mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favorite_grid_cell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FavImage image = images.get(i);
        viewHolder.imageTitle.setText(image.getTitle());
        viewHolder.imageDescription.setText(image.getDescription());
        Glide.with(viewHolder.itemView).load(image.getLink()).into(viewHolder.imagePreview);

        viewHolder.itemView.setTag(image);
        viewHolder.itemView.setOnClickListener(favItemClickListener);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void updateImages(List<FavImage> newImages){
        images = newImages;
        if (newImages != null)
            this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePreview;
        private TextView imageTitle;
        private TextView imageDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePreview = itemView.findViewById(R.id.favoriteImageView);
            imageTitle = itemView.findViewById(R.id.favoriteCellTitle);
            imageDescription = itemView.findViewById(R.id.favoriteCellDescription);
        }
    }
}
