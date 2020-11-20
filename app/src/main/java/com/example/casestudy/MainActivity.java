package com.example.casestudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String MYPREFS = "myprefs";
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String NAMEKEY = "namekey";
    public static final String PWDKEY = "pwdkey";
    EditText nameEditText,pwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText= findViewById(R.id.userName);
        pwdEditText= findViewById(R.id.password);
    }

    /**
     *
     * @param view
     */
    public void handleClick(View view) {
        Log.e(TAG,"handleClick");
        CheckBox checkBox = findViewById(R.id.checkBox);
        switch (view.getId()){
            case R.id.buttonLogin:
                if(validateLogin()) {
                    if (checkBox.isChecked()) {
                        saveData();
                    }
                    startHome();
                }
                break;
        }
    }

    private void register() {
    }

    private void restoreData() {
        //open file
        SharedPreferences preferences= getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //read from the file
        String name = preferences.getString(NAMEKEY,"");
        String pwd = preferences.getString(PWDKEY,"");
        //set data in UI
        nameEditText.setText(name);
        pwdEditText.setText(pwd);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        saveData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restoreData();
    }

    private void saveData() {
        //Get data from UI
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        //Create file named myprefs
        SharedPreferences preferences= getSharedPreferences(MYPREFS,MODE_PRIVATE);
        //open file
        SharedPreferences.Editor editor= preferences.edit();
        //write to the file
        editor.putString(NAMEKEY,name);
        editor.putString(PWDKEY,pwd);
        //save file
        editor.apply();
    }
    private boolean validateLogin() {
        String name = nameEditText.getText().toString();
        String pwd = pwdEditText.getText().toString();
        if(name.equals("admin") && pwd.equals("password")){
            return true;
        }
        return false;
    }

    private void startHome() {
        Log.i(TAG,"Login successful");
        Toast.makeText(this,"Login successful",Toast.LENGTH_SHORT);
        Intent hIntent = new Intent(MainActivity.this, HomeActivity.class);
        hIntent.putExtra("userName",R.id.userName);
        hIntent.putExtra("password",R.id.password);
        startActivity(hIntent);
    }
}