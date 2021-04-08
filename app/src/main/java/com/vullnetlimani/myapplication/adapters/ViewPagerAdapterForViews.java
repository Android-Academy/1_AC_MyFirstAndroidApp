package com.vullnetlimani.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;

public class ViewPagerAdapterForViews extends RecyclerView.Adapter<ViewPagerAdapterForViews.SilderViewHolder> {

    private final LayoutInflater mInflater;
    private final int[] images;

    public ViewPagerAdapterForViews(Context context, int[] images) {
        this.mInflater = LayoutInflater.from(context);
        this.images = images;
    }

    @NonNull
    @Override
    public SilderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = mInflater.inflate(R.layout.image_slider_layout, parent, false);
        return new SilderViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull SilderViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class SilderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public SilderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.gallery_image);
        }
    }
}
