package com.atul.schoolmgt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class slashScreen extends AppCompatActivity {

    private static int Time_out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences(LogIn.PREFS_NAME,0);
                Boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
                String usernmm = sharedPreferences.getString("usernm", "hi");

                if (hasLoggedIn){
                    Intent intent = new Intent(slashScreen.this,Dashboard.class);
                    intent.putExtra("usernmmm",usernmm);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(slashScreen.this,LogIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        },Time_out);
    }
}