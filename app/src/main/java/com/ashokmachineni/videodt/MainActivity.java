package com.ashokmachineni.videodt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyAdapter recyAdapter;
    RecyclerView recyclerView;
    ArrayList<Jobs> jobsArrayList = new ArrayList<Jobs>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getServerData();
    }
    private void getServerData(){
        String urlServer = "http://rest.s3for.me/aaaa/aaa/telugu.json";
        System.out.print(urlServer);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlServer, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("items");
                            for (int m =0;m< jsonArray.length();m++){
                                JSONObject jsonObject = jsonArray.getJSONObject(m);
                                Jobs job = gson.fromJson(String.valueOf(jsonObject),Jobs.class);
                                jobsArrayList.add(job);
                            }
                            recyAdapter = new RecyAdapter(getApplicationContext(),jobsArrayList);
                            recyclerView.setAdapter(recyAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjectRequest);
    }
}
