package com.ashokmachineni.videodt.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashokmachineni.videodt.Model.DownloadedData;
import com.ashokmachineni.videodt.R;
import com.ashokmachineni.videodt.Adapter.downloadAdapter;

import java.io.File;
import java.util.ArrayList;

public class Fragment_Downloads extends android.support.v4.app.Fragment {
    View rootview;
    RecyclerView recyclerView;
    ArrayList<DownloadedData> datalist;
    TextView nodata;
    downloadAdapter adapter;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.freagment_downloads, container, false);
        context = getActivity();
        findview();
        return rootview;
    }

    public void findview() {
        recyclerView = rootview.findViewById(R.id.recViewDownloads);
        nodata = rootview.findViewById(R.id.nodata);
        getFileList();
    }

    public void getFileList() {
        datalist = new ArrayList<>();
        File downloadfolder = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File[] file = downloadfolder.listFiles();
        Log.d("datalistsize", String.valueOf(file.length));
        if (file != null || file.length > 0) {

            //If the file available in these download folder then it shows file names in list
            for (int i = 0; i < file.length; i++) {
                File getfile = file[i];
                DownloadedData data = new DownloadedData();
                data.setName(getfile.getName().toString());
                data.setUri(Uri.fromFile(getfile.getAbsoluteFile()));
                Log.d("Filename", data.getName().toString());
                Log.d("filepath", getfile.getAbsolutePath());
                datalist.add(data);
            }
            setadapter();
        }
        if (datalist.size() < 1) {
            recyclerView.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
        }
    }
    public void setadapter() {
        adapter = new downloadAdapter(datalist, context);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
