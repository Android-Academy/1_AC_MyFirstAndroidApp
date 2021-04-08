package com.vullnetlimani.myapplication.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.activites.DatabaseAct.InsertData;
import com.vullnetlimani.myapplication.activites.DatabaseAct.SearchData;
import com.vullnetlimani.myapplication.activites.DatabaseAct.ViewData;
import com.vullnetlimani.myapplication.util.ToolbarActivity;

public class DatabaseTutorialAct extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_tutorial);
        initToolbar(R.id.mToolbar);

        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnView = findViewById(R.id.btnView);
        Button btnSearch = findViewById(R.id.btnSearch);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insertPage = new Intent(DatabaseTutorialAct.this, InsertData.class);
                startActivity(insertPage);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewData = new Intent(DatabaseTutorialAct.this, ViewData.class);
                startActivity(viewData);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchData = new Intent(DatabaseTutorialAct.this, SearchData.class);
                startActivity(searchData);
            }
        });
    }
}