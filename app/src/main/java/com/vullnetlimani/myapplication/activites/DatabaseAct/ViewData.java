package com.vullnetlimani.myapplication.activites.DatabaseAct;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.adapters.ListDataCursorAdapter;
import com.vullnetlimani.myapplication.util.Constants;
import com.vullnetlimani.myapplication.util.DBHelper;
import com.vullnetlimani.myapplication.util.ToolbarActivity;

public class ViewData extends ToolbarActivity {

    private RecyclerView myRecyclerView;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        initToolbar(R.id.mToolbar);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        dbHelper = new DBHelper(this);

        Cursor cursor = dbHelper.listAllData();

        Log.d(Constants.TAG, "Records - " + cursor.getCount());


        ListDataCursorAdapter listDataCursorAdapter = new ListDataCursorAdapter(this, cursor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(listDataCursorAdapter);

    }
}