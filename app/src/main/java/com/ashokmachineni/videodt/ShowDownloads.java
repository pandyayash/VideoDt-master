package com.ashokmachineni.videodt;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_downloads);
        recyclerView = findViewById(R.id.recViewDownloads);
        nodata = findViewById(R.id.nodata);
        getFileList();
    }

    public void getFileList() {
        datalist = new ArrayList<>();
        File downloadfolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] file = downloadfolder.listFiles();
        Log.d("datalistsize",downloadfolder.list().toString());
        if (file != null) {
            //If the file available in these download folder then it shows file names in list
            for (int i = 0; i < file.length; i++) {
                File getfile = file[i];
                DownloadedData data = new DownloadedData();
                data.setName(getfile.getName());
                Log.d("Filename", data.getName());
                datalist.add(data);
            }
            setadapter();
        } else {
            recyclerView.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
        }

    }

    public void setadapter(){
        adapter=new downloadAdapter(datalist,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
