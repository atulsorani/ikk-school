package com.atul.schoolmgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeworkAdapter extends RecyclerView.Adapter<homeworkAdapter.homeworkHolder> {

    private Context mCtx;
    private List<model_homework> homeworkList;

    public homeworkAdapter(Context mCtx, List<model_homework> homeworkList) {
        this.mCtx = mCtx;
        this.homeworkList = homeworkList;
    }

    @Override
    public homeworkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(mCtx);
        View view = lyf.inflate(R.layout.homeworklayout, null);
        return new homeworkHolder(view);
    }

    @Override
    public void onBindViewHolder(homeworkHolder holder, int position) {
        model_homework quo = homeworkList.get(position);
        holder.textViewdate.setText(quo.getDate());
        holder.textViewQuotes.setText(quo.getQuotes());

    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    class homeworkHolder extends RecyclerView.ViewHolder {

        TextView textViewdate, textViewQuotes;

        public homeworkHolder(View itemView) {
            super(itemView);
            textViewdate = itemView.findViewById(R.id.qdate);
            textViewQuotes = itemView.findViewById(R.id.qdescri);

        }
    }
}
