package com.example.sms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int READ_PHONE_STATE_PERMISSION_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(Manifest.permission.READ_PHONE_STATE, READ_PHONE_STATE_PERMISSION_CODE);
        TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        updateTextView(tm);
        tm.listen(phoneStateListener,phoneStateListener.LISTEN_CALL_STATE);
    }
    PhoneStateListener phoneStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String phoneNumber){
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:  Toast.makeText(MainActivity.this,
                        "IDLE",
                        Toast.LENGTH_SHORT)
                        .show(); break;
                case TelephonyManager.CALL_STATE_OFFHOOK:  Toast.makeText(MainActivity.this,
                        "OFF HOOK",
                        Toast.LENGTH_SHORT)
                        .show(); break;
                case TelephonyManager.CALL_STATE_RINGING:  Toast.makeText(MainActivity.this,
                        "RINGING",
                        Toast.LENGTH_SHORT)
                        .show(); break;
            }
        }
    };
    private void updateTextView(TelephonyManager tm){
        String countryCode=tm.getSimCountryIso();
        String operatorId = tm.getSimOperator();
        String operatorName = tm.getNetworkOperatorName();
        addToTextView(countryCode,operatorId,operatorName);

    }



    private void addToTextView(String cc, String oi, String on){
        TextView countryCode = (TextView)findViewById(R.id.countryCode);
        TextView operatorId = (TextView)findViewById(R.id.operatorId);
        TextView operatorName = (TextView)findViewById(R.id.operatorName);
        countryCode.setText(cc);
        operatorId.setText(oi);
        operatorName.setText(on);
    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(MainActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == READ_PHONE_STATE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "READ_PHONE_STATE Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(MainActivity.this,
                        "READ_PHONE_STATE Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
       
    }

    @Override
    protected void onStop() {

        super.onStop();
        //phoneStateListener.();
    }
}


