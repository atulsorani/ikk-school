package com.atul.schoolmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeWork extends AppCompatActivity {
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/gethomework.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/gethomework.php";
    List<model_homework> homeworkList;
    RecyclerView recyclerView;
    ShimmerFrameLayout frmshim;
    homeworkAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Homework Desk");
        setContentView(R.layout.activity_home_work);
        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();

        homeworkList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.qrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadQuotes();
    }

    private void loadQuotes() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray qUotes = new JSONArray(response);

                    for (int i = qUotes.length()-1; i >= 0; i--) {
                        JSONObject noticeObject = qUotes.getJSONObject(i);
                        String date = noticeObject.getString("date");
                        String quot = noticeObject.getString("quotes");
                        frmshim.stopShimmer();
                        frmshim.setVisibility(View.GONE);
                        model_homework mquotes = new model_homework(date, quot);
                        homeworkList.add(mquotes);
                    }

                    adapter = new homeworkAdapter(HomeWork.this, homeworkList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeWork.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("mobileno", Dashboard.numb);
                return param;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}