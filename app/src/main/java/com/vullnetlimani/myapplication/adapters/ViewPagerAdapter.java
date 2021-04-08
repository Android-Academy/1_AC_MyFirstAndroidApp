package com.vullnetlimani.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vullnetlimani.myapplication.fragments.HomeFragment;
import com.vullnetlimani.myapplication.fragments.LocalNewsFragment;
import com.vullnetlimani.myapplication.fragments.TopNewsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    String[] titles;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String[] titles) {
        super(fragmentActivity);

        this.titles = titles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();

            case 1:
                return new LocalNewsFragment();

            case 2:
                return new TopNewsFragment();

        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
