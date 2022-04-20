package com.atul.schoolmgt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity {

    static TextView userno, std;
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/getname.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getname.php";
    public static String numb;
    ProgressBar proBarr;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        prog();
        LoadName();


        Intent intent = getIntent();
        numb = intent.getStringExtra("usernmmm");



        View logot = findViewById(R.id.logout);
        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(LogIn.PREFS_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("hasLoggedIn");
                editor.remove("usernm");
                editor.commit();

                Intent intent = new Intent(Dashboard.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        View pfre = findViewById(R.id.profile);
        pfre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);
            }
        });

        View hmwork = findViewById(R.id.homeworks);
        hmwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, HomeWork.class);
                startActivity(intent);
            }
        });

        View assignmentss = findViewById(R.id.assignment);
        assignmentss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Assignment.class);
                startActivity(intent);
            }
        });

        View smtrl = findViewById(R.id.material);
        smtrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Material.class);
                startActivity(intent);
            }
        });

        View notic = findViewById(R.id.noticee);
        notic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, notice.class);
                startActivity(intent);
            }
        });

        View quotesss = findViewById(R.id.quotes);
        quotesss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, quotess.class);
                startActivity(intent);
            }
        });

        View Sugge = findViewById(R.id.suggesition);
        Sugge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Suggesions.class);
                startActivity(intent);
            }
        });

    }

    private void prog() {
        proBarr = findViewById(R.id.probar);

        final  Timer t = new Timer();
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

    private void LoadName() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject noticeObject = jsonArray.getJSONObject(0);

                    String names = noticeObject.getString("name");
                    String stds = noticeObject.getString("class");

                    userno = findViewById(R.id.nm);
                    std = findViewById(R.id.enr);

                    proBarr.setVisibility(View.INVISIBLE);
                    userno.setText(names);
                    std.setText("Class : " + stds);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Dashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobileno", numb);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

        Volley.newRequestQueue(this).add(stringRequest);

    }
}