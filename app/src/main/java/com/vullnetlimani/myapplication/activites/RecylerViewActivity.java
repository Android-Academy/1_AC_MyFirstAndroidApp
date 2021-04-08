package com.vullnetlimani.myapplication.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.adapters.RecyclerViewAdapter;
import com.vullnetlimani.myapplication.model.RecyclerViewModel;

import java.util.ArrayList;

import static com.vullnetlimani.myapplication.util.Constants.mobileArray;
import static com.vullnetlimani.myapplication.util.Constants.photoArray;

public class RecylerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mRecyclerView = findViewById(R.id.myRecyclerView);

        ArrayList<RecyclerViewModel> phoneList = new ArrayList<>();

        for (int i = 0; i < photoArray.length; i++) {
            RecyclerViewModel myModel = new RecyclerViewModel(photoArray[i], mobileArray[i] + " - " + i);
            phoneList.add(myModel);
        }


        RecyclerViewAdapter myRecyclerViewAdapter = new RecyclerViewAdapter(this, phoneList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(RecylerViewActivity.this);

        mRecyclerView.setLayoutManager(layoutManager);
        myRecyclerViewAdapter.setClickListener(this);


        mRecyclerView.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("RecyclerLog", "Item Clicked - " + position);
    }
}