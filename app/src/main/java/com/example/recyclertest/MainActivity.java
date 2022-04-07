package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> userList;
    private RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private RecyclerAdapter.RecyclerViewClickListener listener;
    SearchView searchView;

    String[] names = {"eden", "john", "grmsh", "rob"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        userList = new ArrayList<>();
        searchView = findViewById(R.id.search_view);

//        setUserInfo();
        setAdapter();

        for(String s: names) {
            User user = new User(s);
            userList.add(user);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }


    private void setAdapter() {
        setOnClickListener();
        recyclerAdapter = new RecyclerAdapter(userList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username", recyclerAdapter.userList.get(position).getUsername());
                startActivity(intent);
            }
        };
    }

    private void setUserInfo() {
        userList.add(new User("Eden Mubarek"));
        userList.add(new User("john"));
        userList.add(new User("grmsh"));
        userList.add(new User("rob"));
    }

}