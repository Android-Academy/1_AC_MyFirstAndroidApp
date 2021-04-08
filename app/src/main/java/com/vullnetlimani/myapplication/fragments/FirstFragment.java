package com.vullnetlimani.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    RecyclerView myRecyclerView;
    ArrayList<String> itemNews;
    MyFragmentItemSelectedListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MyFragmentItemSelectedListener) {

            this.listener = (MyFragmentItemSelectedListener) context;

        } else {
            throw new ClassCastException(context.toString() + " duhet patjeter klasa te jete e tipit MyFragmentItemSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemNews = new ArrayList<>(Arrays.asList(Constants.newsMenu));
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myRecyclerView = view.findViewById(R.id.myRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(new LocalAdapter(getActivity(), itemNews, new LocalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LocalAdapter.MyViewHolder myViewHolder, int mPosition) {
                Log.d(Constants.TAG, "You pressed - " + mPosition);

                listener.onNewsItemSelected(mPosition);

            }
        }));

    }

    public interface MyFragmentItemSelectedListener {
        void onNewsItemSelected(int position);
    }

    private static class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.MyViewHolder> {

        private final ArrayList<String> mData;
        private final LayoutInflater inflater;
        private final OnItemClickListener onItemClickListener;

        public LocalAdapter(Context context, ArrayList<String> mData, OnItemClickListener onItemClickListener) {
            this.mData = mData;
            this.inflater = LayoutInflater.from(context);
            this.onItemClickListener = onItemClickListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View mView = inflater.inflate(R.layout.simple_local_item_for_fragment_layout, parent, false);
            return new MyViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(mData.get(position));
            holder.bindClickListener(holder, onItemClickListener, position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public interface OnItemClickListener {
            void onItemClick(MyViewHolder myViewHolder, int mPosition);
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.textView);

            }

            public void bindClickListener(MyViewHolder myViewHolder, OnItemClickListener listener, int position) {

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(myViewHolder, position);
                    }
                });

            }

        }
    }

}