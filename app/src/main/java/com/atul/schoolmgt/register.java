package com.atul.schoolmgt;

import android.content.Intent;
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

public class register extends AppCompatActivity {
    TextView reges;
    EditText usernm, pass, rpass;
    Button regesterstd;
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/register.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/register.php";
    ProgressBar proBarr;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Register");
        setContentView(R.layout.activity_register);

        //check number and register user
        regesterstd = findViewById(R.id.btnregister);
        regesterstd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prog();
                adduser();
            }
        });


        //Go to Login
        reges = findViewById(R.id.uglogin);
        reges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //check number here
    private void adduser() {
        usernm = findViewById(R.id.uusername);
        pass = findViewById(R.id.upassword);
        rpass = findViewById(R.id.urepassword);

        final String rusername = usernm.getText().toString().trim();
        final String rapass = pass.getText().toString().trim();
        final String rrpass = rpass.getText().toString().trim();

        if (rusername.isEmpty() || rapass.isEmpty() || rrpass.isEmpty()) {
            Toast.makeText(register.this, "Some Field Is Empty", Toast.LENGTH_SHORT).show();

        } else {
            if (rapass.equals(rrpass)) {

                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        usernm.setText("");
                        pass.setText("");
                        rpass.setText("");

                        Intent intent = new Intent(register.this, LogIn.class);
                        proBarr.setVisibility(View.INVISIBLE);
                        startActivity(intent);
                        finish();

                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        proBarr.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> param = new HashMap<String, String>();
                        param.put("mobileno", rusername);
                        param.put("passw", rapass);
                        return param;
                    }
                };

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(request);

            } else {
                proBarr.setVisibility(View.INVISIBLE);
                Toast.makeText(register.this, "Password Does not Match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void prog() {
        proBarr = findViewById(R.id.probar);
        proBarr.setVisibility(View.VISIBLE);

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                count++;
                proBarr.setProgress(count);
                if (count == 100)
                    t.cancel();
            }
        };
        t.schedule(tt, 0, 100);
    }
}