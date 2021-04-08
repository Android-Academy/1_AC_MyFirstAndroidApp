package com.vullnetlimani.myapplication.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.model.PhoneModelListView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    LayoutInflater inflater;
    List<PhoneModelListView> modelOfPhone;
    PhoneModelListView phoneModel;
    private Activity myActivity;


    public CustomAdapter(Activity myActivity, List<PhoneModelListView> modelOfPhone) {
        this.myActivity = myActivity;
        this.modelOfPhone = modelOfPhone;

        inflater = (LayoutInflater.from(myActivity.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return modelOfPhone.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listview_item_layout, null);
        TextView myTextView;
        ImageView myImageView;

        myTextView = convertView.findViewById(R.id.textView2);
        myImageView = convertView.findViewById(R.id.imageView);


        phoneModel = modelOfPhone.get(position);

        myTextView.setText(phoneModel.getmTitle());
        myImageView.setImageResource(phoneModel.getmPhoto());


      //  myImageView.setColorFilter(Color.parseColor("#1565C0"));
        return convertView;
    }
}
