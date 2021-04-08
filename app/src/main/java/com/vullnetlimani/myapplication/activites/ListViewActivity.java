package com.vullnetlimani.myapplication.activites;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.adapters.CustomAdapter;
import com.vullnetlimani.myapplication.model.PhoneModelListView;

import java.util.ArrayList;
import java.util.List;

import static com.vullnetlimani.myapplication.util.Constants.mobileArray;
import static com.vullnetlimani.myapplication.util.Constants.photoArray;

public class ListViewActivity extends AppCompatActivity {

    private ListView myListView;
    private Toolbar mToolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity_layout);

        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //ArrayAdapter myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mobileArray);

        List<PhoneModelListView> phoneModelList = new ArrayList<>();

        for (int i = 0; i < photoArray.length; i++) {
            PhoneModelListView phoneModelListView = new PhoneModelListView(photoArray[i], mobileArray[i] + " " + i);
            phoneModelList.add(phoneModelListView);
        }


        CustomAdapter myCustomAdapter = new CustomAdapter(this, phoneModelList);

        myListView = findViewById(R.id.myListView);

        myListView.setAdapter(myCustomAdapter);

    }

}
