package com.example.week2java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayUPToast(View v) {

        int btnId = v.getId();
        Log.d("ADebugTag", "Value: " + btnId);
        String msg = "UP";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }
    public void displayDOWNToast(View v) {
        int btnId = v.getId();
        Log.d("ADebugTag", "Value: " + btnId);
        String msg = "DOWN";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }


}
