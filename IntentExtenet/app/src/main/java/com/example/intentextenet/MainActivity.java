package com.example.intentextenet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void launchBrowser(View v){
        Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
        intent.putExtra("dataKey", "https://www.youtube.com");
        startActivity(intent);
    }
}
