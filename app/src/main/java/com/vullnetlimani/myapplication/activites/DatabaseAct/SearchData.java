package com.vullnetlimani.myapplication.activites.DatabaseAct;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.util.Constants;
import com.vullnetlimani.myapplication.util.DBHelper;
import com.vullnetlimani.myapplication.util.ToolbarActivity;

public class SearchData extends ToolbarActivity {

    private SearchView search_view;

    private TextView userID_resource;
    private TextView nameID_resource;
    private TextView dataID_resource;
    private TextView addressID_resource;
    private TextView emailID_resource;

    private Button email_Btn;
    private Button address_btn;
    private Button clear_btn;
    private Button edit_btn;

    private DBHelper myDbHelper;
    private String currentSearchedQuery = "";

    @Override
    protected void onResume() {
        super.onResume();

        if (!currentSearchedQuery.equals(""))
            SearchQuery(currentSearchedQuery);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);
        initToolbar(R.id.mToolbar);

        initData();
        LoadViews();
        searchViewInit();
        buttonInit();

    }

    private void buttonInit() {

        email_Btn.setVisibility(View.GONE);
        address_btn.setVisibility(View.GONE);
        clear_btn.setVisibility(View.GONE);
        edit_btn.setVisibility(View.GONE);


        email_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("mailto:" + emailID_resource.getText().toString());
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(Intent.createChooser(emailIntent, ""));
            }
        });


        address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("geo:0,0?q=" + addressID_resource.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(Intent.createChooser(mapIntent, ""));
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String resID = userID_resource.getText().toString();

                if (resID.equals("")) {

                    Toast.makeText(SearchData.this, "No data to Edit", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentPage = new Intent(SearchData.this, EditViewData.class);
                    intentPage.putExtra(Constants.DATABASE_SEND_ID, resID);
                    startActivity(intentPage);

                    // Intent ke aktivti e re
                }

            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_view.setQuery("", false);
                search_view.clearFocus();
                clearData();
            }
        });
    }

    private void clearData() {

        userID_resource.setText("");
        nameID_resource.setText("");
        dataID_resource.setText("");
        addressID_resource.setText("");
        emailID_resource.setText("");

        email_Btn.setVisibility(View.GONE);
        address_btn.setVisibility(View.GONE);
        clear_btn.setVisibility(View.GONE);
        edit_btn.setVisibility(View.GONE);
    }

    private void searchViewInit() {

        search_view.setSubmitButtonEnabled(true);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(Constants.TAG, "onQueryTextSubmit - " + query);

                currentSearchedQuery = query;

                SearchQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void SearchQuery(String query) {

        Cursor cursor = myDbHelper.searchData(query);
        cursor.moveToFirst();

        if (cursor.getCount() < 1) {

            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();

            clearData();

        } else {

            showData(cursor);

            Toast.makeText(this, "Data found", Toast.LENGTH_SHORT).show();
        }

        if (!cursor.isClosed())
            cursor.close();
    }

    private void showData(Cursor cursor) {

        String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
        String data = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATA));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
        String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));


        userID_resource.setText(id);
        nameID_resource.setText(name);
        dataID_resource.setText(data);
        addressID_resource.setText(address);
        emailID_resource.setText(email);

        email_Btn.setVisibility(View.VISIBLE);
        address_btn.setVisibility(View.VISIBLE);
        clear_btn.setVisibility(View.VISIBLE);
        edit_btn.setVisibility(View.VISIBLE);
    }

    private void initData() {
        myDbHelper = new DBHelper(this);
    }

    private void LoadViews() {

        search_view = findViewById(R.id.search_view);

        userID_resource = findViewById(R.id.userID_resource);
        nameID_resource = findViewById(R.id.nameID_resource);
        dataID_resource = findViewById(R.id.dataID_resource);
        addressID_resource = findViewById(R.id.addressID_resource);
        emailID_resource = findViewById(R.id.emailID_resource);

        email_Btn = findViewById(R.id.email_Btn);
        address_btn = findViewById(R.id.address_btn);
        clear_btn = findViewById(R.id.clear_btn);
        edit_btn = findViewById(R.id.edit_btn);

    }
}