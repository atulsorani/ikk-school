package com.atul.schoolmgt;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class notice extends AppCompatActivity {

    //private static final String NOTICE_URL = "http://192.168.43.244:8080/simple_crud/api/getnotice.php";
    private static final String NOTICE_URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getnotice.php";
    List<model_notice> noticeList;
    RecyclerView recyclerView;
    ShimmerFrameLayout frmshim;
    noticeAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notice Board");
        setContentView(R.layout.activity_notice);

        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();

        noticeList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadNotice();


    }

    private void loadNotice() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NOTICE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray nOtice = new JSONArray(response);

                    for (int i = nOtice.length()-1; i >= 0; i--) {
                        JSONObject noticeObject = nOtice.getJSONObject(i);
                        String title = noticeObject.getString("title");
                        String date = noticeObject.getString("date");
                        String desc = noticeObject.getString("description");

                        frmshim.stopShimmer();
                        frmshim.setVisibility(View.GONE);
                        model_notice mnotice = new model_notice(title, date, desc);
                        noticeList.add(mnotice);
                    }

                    adapter = new noticeAdapter(notice.this, noticeList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(notice.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}