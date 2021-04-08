package com.vullnetlimani.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.util.Constants;

public class SecondFragment extends Fragment {
    private TextView mTitle;
    private TextView mDetail;
    private int myValue = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            if (getArguments() != null) {
                myValue = getArguments().getInt(Constants.FRAGMENT_SEND_STRING, 0);
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle = view.findViewById(R.id.mTitle);
        mDetail = view.findViewById(R.id.mDetail);


        mTitle.setText(Constants.newsMenu[myValue]);
        mDetail.setText(Constants.newsDetail[myValue]);

    }
}