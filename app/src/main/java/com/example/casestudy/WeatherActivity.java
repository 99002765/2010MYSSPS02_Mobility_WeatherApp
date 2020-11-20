package com.example.casestudy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = WeatherActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView textView= findViewById(R.id.textView);
        textView.setText(this.getIntent().getStringExtra("object"));
        //Log.d(TAG, "onCreate: ");
    }
}