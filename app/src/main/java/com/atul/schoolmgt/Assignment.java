package com.atul.schoolmgt;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Assignment extends AppCompatActivity {

    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/getassignment.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getassignment.php";
    List<model_assignment> assignmentList;
    RecyclerView recyclerView;
    assignmentAdapter adapter = null;
    ShimmerFrameLayout frmshim;
    static String gddlink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Assignment Desk");
        setContentView(R.layout.activity_assignment);
        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();

        assignmentList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.MrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadMaterial();


    }

    private void loadMaterial() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = jsonArray.length()-1; i >= 0; i--) {
                        JSONObject noticeObject = jsonArray.getJSONObject(i);
                        String subnm = noticeObject.getString("subjectname");
                        gddlink = noticeObject.getString("googlelink");
                        //String orgdrlink = "http://192.168.43.244:8080/simple_crud/api/docacc/"+gddlink;
                        String orgdrlink = "https://atulsproject.000webhostapp.com/simple_crud/api/assignmentdoc/"+gddlink;
                        frmshim.stopShimmer();
                        frmshim.setVisibility(View.GONE);
                        model_assignment assignment = new model_assignment(subnm,orgdrlink);
                        assignmentList.add(assignment);
                    }

                    adapter = new assignmentAdapter(Assignment.this, assignmentList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Assignment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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