package com.example.casestudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.casestudy.weatherFile.WeatherActivity;

public class HomeActivity extends AppCompatActivity implements MyAdapter.OnCityListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    String[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        data = new String[]{"Mumbai", "Bangalore", "Mysore", "Hyderabad", "Chennai","Bhopal","Bhubaneshwar","Indore","Guwahati","Patna","Kolkata","Ranchi","Srinagar","Delhi","Vijayawada","Khammam","Chandigarh","Gandhinagar","Jaipur",};
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