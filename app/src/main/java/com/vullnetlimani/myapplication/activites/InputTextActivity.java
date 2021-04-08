package com.vullnetlimani.myapplication.activites;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.vullnetlimani.myapplication.R;

public class InputTextActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextInputEditText editTextTextPersonName;
    private TextInputEditText editTextTextPassword;
    private Button mButton;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_text);

        mToolbar = findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        mButton = findViewById(R.id.button);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextTextPersonName.getText().toString();
                String pass = editTextTextPassword.getText().toString();

                String message = getString(R.string.snackbar_message, name.toUpperCase(), pass);

                Snackbar mSnackbar = Snackbar

                        .make(findViewById(R.id.parentLayout)
                                , message
                                , Snackbar.LENGTH_LONG)

                        .setBackgroundTint(getResources().getColor(R.color.primary_dark))
                        .setActionTextColor(Color.WHITE)
                        .setTextColor(Color.WHITE)
                        .setAction(R.string.toast_in, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(InputTextActivity.this, message, Toast.LENGTH_LONG).show();
                            }
                        });


                mSnackbar.show();

            }
        });

    }
}