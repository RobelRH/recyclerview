package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView nameTxt = findViewById(R.id.nameText);

        String username = "username not set";

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            username = extras.getString("username");
        }

        nameTxt.setText(username);

    }
}