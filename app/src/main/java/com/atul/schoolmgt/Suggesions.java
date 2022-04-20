package com.atul.schoolmgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class Suggesions extends AppCompatActivity {

    Button sugbtn;
    EditText nam, suggest;
    public static String namee, suggg;
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/insr.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/insr.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Suggestion");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggesions);

        nam = findViewById(R.id.namee);
        suggest = findViewById(R.id.suggest);


        nam.setText((CharSequence) Dashboard.userno.getText().toString());

        sugbtn = findViewById(R.id.subtn);
        sugbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                namee = nam.getText().toString().trim();
                suggg = suggest.getText().toString().trim();

                if(suggg.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Suggestion !", Toast.LENGTH_SHORT).show();
                    
                }else {
                    insertSuggesiton();
                }
            }
        });
    }

    private void insertSuggesiton() {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                nam.setText("");
                suggest.setText("");
                Intent intent = new Intent(Suggesions.this, Suggpro.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("name", namee);
                param.put("sugg", suggg);
                return param;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}