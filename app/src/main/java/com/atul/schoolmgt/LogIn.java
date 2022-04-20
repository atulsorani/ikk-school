package com.atul.schoolmgt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LogIn extends AppCompatActivity {

    TextView lgin;
    EditText usernm, pass;
    Button btnlgn;
    public static String lusernm = "";
    public static String PREFS_NAME = "MyPrefsFilee";
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/loginuser.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/loginuser.php";
    ProgressBar proBarr;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Log In");
        setContentView(R.layout.activity_log_in);

        btnlgn = findViewById(R.id.btnlogin);
        usernm = findViewById(R.id.luser);
        pass = findViewById(R.id.lpassword);

        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernm.equals("") && pass.equals("")) {
                    Toast.makeText(LogIn.this, "Enter Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    prog();
                    starLogin();
                }
            }
        });


        //Go to register
        lgin = findViewById(R.id.gregi);
        lgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, register.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void starLogin() {

        lusernm = usernm.getText().toString().trim();
        final String lpass = pass.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("1")) {

                    SharedPreferences sharedPreferences = getSharedPreferences(LogIn.PREFS_NAME, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.putString("usernm", lusernm);
                    String tempnm = sharedPreferences.getString("usernm", lusernm);
                    editor.commit();

                    try {
                        Intent intent = new Intent(LogIn.this, Dashboard.class);
                        intent.putExtra("usernmmm", lusernm);
                        proBarr.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                        finish();

                    } catch (Exception e) {
                        proBarr.setVisibility(View.INVISIBLE);
                        Toast.makeText(LogIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    proBarr.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogIn.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobileno", lusernm);
                param.put("passw", lpass);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void prog() {
        proBarr = findViewById(R.id.probar);
        proBarr.setVisibility(View.VISIBLE);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                count ++;
                proBarr.setProgress(count);
                if (count == 100)
                    t.cancel();
            }
        };
        t.schedule(tt,0,100);
    }
}