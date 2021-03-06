package com.ashokmachineni.videodt.Activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokmachineni.videodt.R;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button plabut, dowbut, showdownload;
    String vidurl;
    String names, imaur;
    DownloadManager downloadManager;
    SharedPreferences preferences;
    TextView txtview;
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findviews();
        setonclick();
    }

    //find all views to Activity
    public void findviews() {
        plabut = findViewById(R.id.playbut);
        dowbut = findViewById(R.id.downbut);
        txtview = findViewById(R.id.textst);
        imgv = findViewById(R.id.imge);
        preferences = getSharedPreferences("mydata", MODE_PRIVATE);
        names = preferences.getString("titles", null);
        if (names != null) {
            names = preferences.getString("titles", null);
            imaur = preferences.getString("images", null);
            vidurl = preferences.getString("vurl", null);
            setdata(names, imaur, vidurl);
        }
    }

    //Setting click on all views
    public void setonclick() {
        plabut.setOnClickListener(this);
        dowbut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playbut:
                Intent intent = new Intent(DetailsActivity.this, ExActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("urlex", vidurl);
                startActivity(intent);
                break;
            case R.id.downbut:
                download(vidurl, names);
                break;
        }
    }

    //Function to Download video
    public void download(String url, String name) {

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);
        Toast.makeText(this, "Please wait your file is Downloading", Toast.LENGTH_SHORT).show();
    }

    //Set data on Views
    public void setdata(String name, String Image, String Url) {
        txtview.setText(names);
        Glide.with(this)
                .load(imaur)
                .into(imgv);
    }


}
