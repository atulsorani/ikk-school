package com.atul.schoolmgt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class assignmentAdapter extends RecyclerView.Adapter<assignmentAdapter.assignmentHolder> {

    private Context mCtx;
    private String url;
    private List<model_assignment> assignmentList;


    public assignmentAdapter(Context mCtx, List<model_assignment> assignmentList) {
        this.mCtx = mCtx;
        this.assignmentList = assignmentList;
    }

    @Override
    public assignmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(mCtx);
        View view = lyf.inflate(R.layout.assignmentlayout, null);
        return new assignmentHolder(view);
    }

    @Override
    public void onBindViewHolder(assignmentHolder holder, int position) {
        model_assignment material = assignmentList.get(position);
        holder.textViewsubject.setText(material.getSubnmm());
        holder.textlink.setText("Download");
        String urll = material.getBtndown();
        holder.textlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urll));
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    class assignmentHolder extends RecyclerView.ViewHolder {

        TextView textViewsubject, textlink;

        public assignmentHolder(View itemView) {
            super(itemView);
            textViewsubject = itemView.findViewById(R.id.subjectnm);
            textlink = itemView.findViewById(R.id.dwnbtn);
        }
    }
}
