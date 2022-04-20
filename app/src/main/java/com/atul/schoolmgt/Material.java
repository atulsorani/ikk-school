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
import com.android.volley.RequestQueue;
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

public class Material extends AppCompatActivity {

    //private static final String URL = "http://192.168.43.244:8080/simple_crud/api/getmaterial.php";
    private static final String URL = "https://atulsproject.000webhostapp.com/simple_crud/api/getmaterial.php";
    List<model_material> materialList;
    RecyclerView recyclerView;
    ShimmerFrameLayout frmshim;
    materialAdapter adapter = null;
    static String gddlink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Material Desk");
        setContentView(R.layout.activity_material);
        frmshim = findViewById(R.id.shra);
        frmshim.startShimmer();

        materialList = new ArrayList<>();
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
                        //String orgdrlink = "http://192.168.43.244:8080/simple_crud/api/doc/"+gddlink;
                        String orgdrlink = "https://atulsproject.000webhostapp.com/simple_crud/api/doc/"+gddlink;

                        frmshim.stopShimmer();
                        frmshim.setVisibility(View.GONE);
                        model_material material = new model_material(subnm,orgdrlink);
                        materialList.add(material);
                    }

                    adapter = new materialAdapter(Material.this, materialList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Material.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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