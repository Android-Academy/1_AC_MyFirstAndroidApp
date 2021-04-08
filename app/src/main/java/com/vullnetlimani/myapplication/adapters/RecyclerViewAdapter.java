package com.vullnetlimani.myapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.model.RecyclerViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<RecyclerViewModel> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public void setSetIconColor(int setIconColor) {
        this.setIconColor = setIconColor;
    }

    private int setIconColor;

    public RecyclerViewAdapter(Context mContext, List<RecyclerViewModel> mData) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;

        Log.d("TestLog", "Adapter Created");

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("TestLog", "onCreateViewHolder");

        View mView = mInflater.inflate(R.layout.item_for_recyler_view, parent, false);

        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerViewModel myModel = mData.get(position);

        holder.myTitleTextView.setText(myModel.getmTitle());
        holder.imageView.setImageResource(myModel.getPhotoID());


        Log.d("TestLog", "Position - " + position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTitleTextView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            myTitleTextView = itemView.findViewById(R.id.myTitleTextView);

            imageView.setColorFilter(setIconColor);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(v, getAdapterPosition());
        }


    }
}
