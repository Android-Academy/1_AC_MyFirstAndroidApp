package com.vullnetlimani.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.vullnetlimani.myapplication.activites.About;
import com.vullnetlimani.myapplication.activites.Calculator;
import com.vullnetlimani.myapplication.activites.DatabaseTutorialAct;
import com.vullnetlimani.myapplication.activites.FragmentActivity;
import com.vullnetlimani.myapplication.activites.InputTextActivity;
import com.vullnetlimani.myapplication.activites.ListViewActivity;
import com.vullnetlimani.myapplication.activites.RecylerViewActivity;
import com.vullnetlimani.myapplication.activites.TicTacToeGame;
import com.vullnetlimani.myapplication.activites.ViewPagerAcitivty;
import com.vullnetlimani.myapplication.activites.ViewPagerActivity_for_Views;
import com.vullnetlimani.myapplication.adapters.RecyclerViewAdapter;
import com.vullnetlimani.myapplication.model.RecyclerViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private TypedArray photoArray;
    private String[] itemNames;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ImageView my_avatar;
    private TextView current_textView;
    private TextView current_student_textView;
    private DrawerLayout drawerLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about_id:
                About.start(MainActivity.this);
                break;
            case R.id.share_id:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello i am sending this text from my app.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        loadViews();
        initListView();
        initNav();
    }

    private void initNav() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.open_nav_drawer, R.string.close_nav_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    private void loadViews() {
        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        mRecyclerView = findViewById(R.id.myRecyclerView);
        my_avatar = findViewById(R.id.my_avatar);
        current_textView = findViewById(R.id.current_textView);
        current_student_textView = findViewById(R.id.current_student_textView);
    }

    private void initListView() {

        itemNames = getResources().getStringArray(R.array.main_activity_list_array);
        photoArray = getResources().obtainTypedArray(R.array.main_activity_photos);

        List<RecyclerViewModel> myNormalList = new ArrayList<>();

        for (int i = 0; i < itemNames.length; i++) {

            RecyclerViewModel mRecyclerViewModel = new RecyclerViewModel(photoArray.getResourceId(i, -1), i + 1 + "." + itemNames[i]);
            myNormalList.add(mRecyclerViewModel);


        }

        RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, myNormalList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter.setClickListener(this);
        mRecyclerViewAdapter.setSetIconColor(Color.BLACK);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startIntent(ListViewActivity.class);
                break;
            case 1:
                startIntent(TicTacToeGame.class);
                break;
            case 2:
                startIntent(Calculator.class);
                break;
            case 3:
                startIntent(RecylerViewActivity.class);
                break;
            case 4:
                startIntent(InputTextActivity.class);
                break;
            case 5:
                startIntent(FragmentActivity.class);
                break;
            case 6:
                startIntent(ViewPagerAcitivty.class);
                break;
            case 7:
                startIntent(ViewPagerActivity_for_Views.class);
                break;
            case 8:
                startIntent(DatabaseTutorialAct.class);
                break;
            default:
                break;
        }
    }

    private void startIntent(Class<?> theClassName) {
        Intent newIntent = new Intent(MainActivity.this, theClassName);
        startActivity(newIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);

        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (item.getItemId()) {
                    case R.id.listview_id:
                        startIntent(ListViewActivity.class);
                        break;
                    case R.id.tictactoe_id:
                        startIntent(TicTacToeGame.class);
                        break;
                    case R.id.calculator_id:
                        startIntent(Calculator.class);
                        break;
                    case R.id.recyclerview_id:
                        startIntent(RecylerViewActivity.class);
                        break;
                    case R.id.input_text_id:
                        startIntent(InputTextActivity.class);
                        break;
                    case R.id.fragment_id:
                        startIntent(FragmentActivity.class);
                        break;
                    case R.id.view_pager_id:
                        startIntent(ViewPagerAcitivty.class);
                        break;
                    case R.id.view_pager_gallery_id:
                        startIntent(ViewPagerActivity_for_Views.class);
                        break;
                    case R.id.data_base_id:
                        startIntent(DatabaseTutorialAct.class);
                        break;
                    case R.id.about_id:
                        startIntent(About.class);
                        break;
                }
            }
        }, 300);


        return true;
    }
}