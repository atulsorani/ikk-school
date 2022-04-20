package com.atul.schoolmgt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View logot = findViewById(R.id.logout);
        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(LogIn.PREFS_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("hasLoggedIn");
                editor.commit();

                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        View assignmentss = findViewById(R.id.assignment);
        assignmentss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Assignment.class);
                startActivity(intent);
            }
        });

        View smtrl = findViewById(R.id.material);
        smtrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Material.class);
                startActivity(intent);
            }
        });

        View notic = findViewById(R.id.noticee);
        notic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, notice.class);
                startActivity(intent);
            }
        });



        View quotesss = findViewById(R.id.quotes);
        quotesss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, quotess.class);
                startActivity(intent);
            }
        });

        View Sugge = findViewById(R.id.suggesition);
        Sugge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Suggesions.class);
                startActivity(intent);
            }
        });
    }


}