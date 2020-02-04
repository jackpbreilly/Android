package com.example.intentextenet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void launchBrowser(View v){
        Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
        String URL = getURLFromTextbox();
        intent.putExtra("dataKey", URL);
        startActivity(intent);
    }

    public void launchDial(View v){
        EditText textbox = findViewById(R.id.editText);
        String num = textbox.getText().toString();
        Uri number = Uri.parse("tel:"+num);
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(intent);
    }
    public void launchContacts(View v){
        EditText textbox = findViewById(R.id.editText);
        String num = textbox.getText().toString();
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, num);
        startActivity(intent);
    }


    public String getURLFromTextbox(){
        EditText textbox = findViewById(R.id.editText);
        String URL = textbox.getText().toString();
        return URL;
    }
}
