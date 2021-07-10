package com.example.soc.main;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.soc.R;
import com.example.soc.login.LoginActivity;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    private void startLoading() {
        SharedPreferences pref = getSharedPreferences("PREF", MODE_PRIVATE);
        if (pref.getString("id", "").isEmpty() == false) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                    finish();
                }
            }, 2000);

        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    finish();
                }
            }, 2000);


        }
    }
}
