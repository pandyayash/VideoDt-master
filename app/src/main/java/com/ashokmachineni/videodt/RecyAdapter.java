package com.ashokmachineni.videodt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MiViews> {
    Context context;
    ArrayList<Jobs> jobs;

    public RecyAdapter(Context context, ArrayList<Jobs> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @Override
    public MiViews onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.from(parent.getContext()).inflate(R.layout.rowlist,parent,false);
       MiViews miViews = new MiViews(view);
        return miViews;
    }

    @Override
    public void onBindViewHolder(final MiViews holder, final int position) {
        final Jobs job = jobs.get(position);
        holder.texts.setText(job.getTitle());
        final String imgUrl = job.getThumbnail();
        Glide.with(context)
                .load(imgUrl)
                .thumbnail(0.5f)
                .into(holder.images);
        final String vurl = job.getUrl();
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                /*intent.putExtra("titles",job.getTitle());
                intent.putExtra("images",imgUrl);
                intent.putExtra("url",vurl);*/
                SharedPreferences.Editor editor = context.getSharedPreferences("mydata", MODE_PRIVATE).edit();
                editor.putString("titles", job.getTitle());
                editor.putString("images", job.getThumbnail());
                editor.putString("vurl", job.getUrl());
                editor.apply();
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class MiViews extends RecyclerView.ViewHolder {
        ImageView images;
        TextView texts;
        LinearLayout linearLayout;
        public MiViews(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.imgscl);
            texts = itemView.findViewById(R.id.texname);
            linearLayout = itemView.findViewById(R.id.llview);
        }
    }
}
