package com.vullnetlimani.myapplication.activites.DatabaseAct;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.vullnetlimani.myapplication.R;
import com.vullnetlimani.myapplication.util.Constants;
import com.vullnetlimani.myapplication.util.DBHelper;
import com.vullnetlimani.myapplication.util.ToolbarActivity;

public class EditViewData extends ToolbarActivity {

    private Button update_btn;
    private Button delete_btn;

    private TextInputEditText TextInputEditText_userID;
    private TextInputEditText TextInputEditText_name;
    private TextInputEditText TextInputEditText_data;
    private TextInputEditText TextInputEditText_address;
    private TextInputEditText TextInputEditText_email;

    private String getUserID = "";
    private DBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_data);
        initToolbar(R.id.mToolbar);

        initData();
        LoadViews();
        manageData();
        initButton();
    }

    private void initButton() {

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = TextInputEditText_userID.getText().toString();
                String name = TextInputEditText_name.getText().toString();
                String data = TextInputEditText_data.getText().toString();
                String address = TextInputEditText_address.getText().toString();
                String email = TextInputEditText_email.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(EditViewData.this);

                alert.setTitle("Update");
                alert.setMessage("Are you sure you want to update this data?");

                alert.setPositiveButton("PO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (myDbHelper.updateData(id, name, data, address, email)) {
                            Toast.makeText(EditViewData.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditViewData.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                        finish();
                    }
                });

                alert.setNegativeButton("JO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = TextInputEditText_userID.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(EditViewData.this);

                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete this data?");

                alert.setPositiveButton("PO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (myDbHelper.deleteData(id)) {
                            Toast.makeText(EditViewData.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditViewData.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                        finish();
                    }
                });

                alert.setNegativeButton("JO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

    }

    private void manageData() {
        Cursor cursor = myDbHelper.searchData(getUserID);
        cursor.moveToFirst();


        if (cursor.getCount() < 1) {
            Toast.makeText(this, "Ska te dhena", Toast.LENGTH_SHORT).show();
        } else {

            String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_DATA));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ADDRESS));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_EMAIL));


            TextInputEditText_userID.setText(id);
            TextInputEditText_name.setText(name);
            TextInputEditText_data.setText(data);
            TextInputEditText_address.setText(address);
            TextInputEditText_email.setText(email);


            Toast.makeText(this, "Data found", Toast.LENGTH_SHORT).show();
        }

        if (!cursor.isClosed())
            cursor.close();
    }

    private void initData() {

        myDbHelper = new DBHelper(this);
        Intent intentPage = getIntent();
        getUserID = intentPage.getStringExtra(Constants.DATABASE_SEND_ID);

        Log.d(Constants.TAG, getUserID);
    }

    private void LoadViews() {


        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        TextInputEditText_userID = findViewById(R.id.TextInputEditText_userID);
        TextInputEditText_name = findViewById(R.id.TextInputEditText_name);
        TextInputEditText_data = findViewById(R.id.TextInputEditText_data);
        TextInputEditText_address = findViewById(R.id.TextInputEditText_address);
        TextInputEditText_email = findViewById(R.id.TextInputEditText_email);

    }
}