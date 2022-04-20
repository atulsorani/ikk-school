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

public class materialAdapter extends RecyclerView.Adapter<materialAdapter.materialHolder> {

    private Context mCtx;
    private List<model_material> materialList;


    public materialAdapter(Context mCtx, List<model_material> materialList) {
        this.mCtx = mCtx;
        this.materialList = materialList;
    }

    @Override
    public materialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(mCtx);
        View view = lyf.inflate(R.layout.materiallayout, null);
        return new materialHolder(view);
    }

    @Override
    public void onBindViewHolder(materialHolder holder, int position) {
        model_material material = materialList.get(position);
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
        return materialList.size();
    }

    class materialHolder extends RecyclerView.ViewHolder {

        TextView textViewsubject, textlink;

        public materialHolder(View itemView) {
            super(itemView);
            textViewsubject = itemView.findViewById(R.id.subjectnm);
            textlink = itemView.findViewById(R.id.dwnbtn);
        }
    }
}
