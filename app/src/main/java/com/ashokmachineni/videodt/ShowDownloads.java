package com.ashokmachineni.videodt;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ShowDownloads extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<DownloadedData> datalist;
    TextView nodata;
    downloadAdapter adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_downloads);
        recyclerView = findViewById(R.id.recViewDownloads);
        nodata = findViewById(R.id.nodata);
        getFileList();
        context=this;
    }

    public void getFileList() {
        datalist = new ArrayList<>();
        File downloadfolder = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
