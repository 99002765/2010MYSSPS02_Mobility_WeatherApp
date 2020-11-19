package com.example.casestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String[] languagesData = new String[]{"Mumbai", "Bangalore", "Mysore", "Hyderabad", "Chennai"};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this,languagesData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}