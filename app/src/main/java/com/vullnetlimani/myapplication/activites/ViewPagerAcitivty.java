package com.vullnetlimani.myapplication.activites;

import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.adapters.ViewPagerAdapter;

public class ViewPagerAcitivty extends AppCompatActivity {

    private final String[] titles = new String[]{"Home", "Local", "Top"};
    private final int[] icon = new int[]{R.drawable.ic_home, R.drawable.ic_local, R.drawable.ic_top_icon};
    private Toolbar mToolbar;
    private ViewPager2 mViewPager2;
    private TabLayout tabLayout;
    private AppBarLayout mAppBarLayout;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_acitivty);

        mAppBarLayout = findViewById(R.id.mAppBarLayout);
        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            StateListAnimator stateListAnimator = new StateListAnimator();
            stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(mToolbar, "elevation", 0));
            mAppBarLayout.setStateListAnimator(stateListAnimator);
        }

        tabLayout = findViewById(R.id.tabLayout);
        mViewPager2 = findViewById(R.id.mViewPager2);


        tabLayout.setBackgroundColor(getResources().getColor(R.color.primary));
        tabLayout.setTabRippleColor(ColorStateList.valueOf(Color.WHITE));
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);

        tabLayout.setTabTextColors(getResources().getColor(R.color.primary_dark), Color.WHITE);

        ColorStateList myColorStateList = new ColorStateList(

                new int[][]{
                        new int[]{android.R.attr.state_selected},
                        new int[]{android.R.attr.state_enabled},
                },

                new int[]{
                        Color.WHITE,
                        getResources().getColor(R.color.primary_dark)
                }

        );

        tabLayout.setTabIconTint(myColorStateList);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, titles);
        mViewPager2.setAdapter(viewPagerAdapter);

        mViewPager2.setCurrentItem(2, false);

        new TabLayoutMediator(tabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
                tab.setIcon(icon[position]);
            }
        }).attach();

        //tabLayout.getTabAt(2).select();

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}