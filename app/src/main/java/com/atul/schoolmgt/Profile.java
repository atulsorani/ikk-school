package com.atul.schoolmgt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class Profile extends AppCompatActivity {

    TextView usernm,std,city,monile,pmobile;
    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/getprofiledata.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getprofiledata.php";
    ShimmerFrameLayout frmshim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");
        setContentView(R.layout.activity_profile);
        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();
        frmshim.setVisibility(View.VISIBLE);
        LoadName();
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
                    String cty = noticeObject.getString("city");
                    String mobile = noticeObject.getString("mobileno");
                    String pmob = noticeObject.getString("parentmbo");

                    frmshim.stopShimmer();
                    frmshim.setVisibility(View.GONE);

                    usernm = findViewById(R.id.name);
                    std = findViewById(R.id.classs);
                    city = findViewById(R.id.city);
                    monile = findViewById(R.id.mobileno);
                    pmobile = findViewById(R.id.pmno);

                    usernm.setText("Name : " + names);
                    std.setText("Class : " + stds);
                    city.setText("City : " + cty);
                    monile.setText("Mobile No : " + mobile);
                    pmobile.setText("Parent Mobile No : " + pmob);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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