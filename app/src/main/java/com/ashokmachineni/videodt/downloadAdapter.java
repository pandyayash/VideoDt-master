package com.ashokmachineni.videodt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class downloadAdapter extends RecyclerView.Adapter<downloadAdapter.Viewholder> {
    ArrayList<DownloadedData> datalist;
    Context context;

    public downloadAdapter(ArrayList<DownloadedData> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.downloadlist_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        DownloadedData model=datalist.get(position);
        holder.textView.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView textView;
        public Viewholder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);
        }
    }
}
