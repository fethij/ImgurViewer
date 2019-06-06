package com.example.imgurviewer.UI.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imgurviewer.Models.Api.Image;
import com.example.imgurviewer.R;

import java.util.List;

public class favoritesAdapter extends RecyclerView.Adapter<favoritesAdapter.ViewHolder> {

    private List<Image> images;

    public favoritesAdapter(List<Image> imageList){
        images = imageList;
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
        Image image = images.get(i);
        viewHolder.imageTitle.setText(image.getTitle());
        viewHolder.imageDescription.setText(image.getDescription());
        Glide.with(viewHolder.itemView).load(image.getLink()).into(viewHolder.imagePreview);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void updateImages(List<Image> newImages){
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
