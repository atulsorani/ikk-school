package com.atul.schoolmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class quotess extends AppCompatActivity {
    //private static final String QUOTES_URL = "http://192.168.43.244:8080/simple_crud/api/getquotes.php";
    private static final String QUOTES_URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getquotes.php";
    List<model_quotes> quotesList;
    ShimmerFrameLayout frmshim;
    RecyclerView recyclerView;
    quotesAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quotes");
        setContentView(R.layout.activity_quotess);
        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();
        quotesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.qrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadQuotes();
    }

    private void loadQuotes() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, QUOTES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray qUotes = new JSONArray(response);

                    for (int i = qUotes.length()-1; i >= 0; i--) {
                        JSONObject noticeObject = qUotes.getJSONObject(i);
                        String date = noticeObject.getString("date");
                        String squot = noticeObject.getString("quotes");
                        //String quot = "http://192.168.43.244:8080/simple_crud/api/images/"+squot;
                        String quot = "https://atulsproject.000webhostapp.com/simple_crud/api/images/"+squot;
                        String desc = noticeObject.getString("desc");

                        frmshim.stopShimmer();
                        frmshim.setVisibility(View.GONE);
                        model_quotes mquotes = new model_quotes(date, quot, desc);
                        quotesList.add(mquotes);
                    }

                    adapter = new quotesAdapter(quotess.this, quotesList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(quotess.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}