package com.ashokmachineni.videodt.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ashokmachineni.videodt.Model.Jobs;
import com.ashokmachineni.videodt.R;
import com.ashokmachineni.videodt.Adapter.RecyAdapter;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Home extends Fragment {
    View rootview;
    Context context;
    RecyAdapter recyAdapter;
    RecyclerView recyclerView;
    ArrayList<Jobs> jobsArrayList = new ArrayList<Jobs>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=inflater.inflate(R.layout.fragment_home,container,false);
        context=getActivity();
        findview();
        return rootview;
    }
    public void findview(){
        recyclerView = rootview.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
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
                            recyAdapter = new RecyAdapter(context,jobsArrayList);
                            recyclerView.setAdapter(recyAdapter);
                            recyAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
    }

}
