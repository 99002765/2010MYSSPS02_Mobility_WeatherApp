package com.example.casestudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeActivity extends AppCompatActivity implements MyAdapter.OnCityListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data = new String[]{"Mumbai", "Bangalore", "Mysore", "Hyderabad", "Chennai","Bhopal","Bhubaneshwar","Indore","Gawhati","Patna","Kolkata","Ranchi","Srinagar","Delhi"};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this,data,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCityClick(int position) {
        //data[position];
        Log.d(TAG, "onCityClick: clicked"+position);
        Intent intent= new Intent(this,WeatherActivity.class);
        intent.putExtra("object", data[position]);
        String code=NetworkUtils.getCityInfo(data[position]);
        intent.putExtra("Json",code);
        startActivity(intent);
    }
}