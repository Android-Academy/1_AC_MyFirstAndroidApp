package com.vullnetlimani.myapplication.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.adapters.ViewPagerAdapterForViews;

public class ViewPagerActivity_for_Views extends AppCompatActivity {

    private final int[] images = new int[]{R.drawable.ic_breakfast, R.drawable.ic_sandwich, R.drawable.ic_spaghetti};
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_for__views);

        viewPager2 = findViewById(R.id.viewpager_id);

        ViewPagerAdapterForViews viewPagerAdapterForViews = new ViewPagerAdapterForViews(this, images);
        viewPager2.setAdapter(viewPagerAdapterForViews);

    }
}
