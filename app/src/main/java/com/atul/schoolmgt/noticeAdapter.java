package com.atul.schoolmgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class noticeAdapter extends RecyclerView.Adapter<noticeAdapter.noticeHolder> {

    private Context mCtx;
    private List<model_notice> noticeList;

    public noticeAdapter(Context mCtx, List<model_notice> noticeList) {
        this.mCtx = mCtx;
        this.noticeList = noticeList;
    }

    @Override
    public noticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(mCtx);
        View view = lyf.inflate(R.layout.listlayout, null);
        return new noticeHolder(view);
    }

    @Override
    public void onBindViewHolder(noticeHolder holder, int position) {
        model_notice notice = noticeList.get(position);
        holder.textViewtitle.setText(notice.getTitle());
        holder.textViewdate.setText(notice.getDate());
        holder.textViewdesc.setText(notice.getDesc());

    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    class noticeHolder extends RecyclerView.ViewHolder {

        TextView textViewtitle, textViewdate, textViewdesc;

        public noticeHolder(View itemView) {
            super(itemView);
            textViewtitle = itemView.findViewById(R.id.ltite);
            textViewdate = itemView.findViewById(R.id.ldate);
            textViewdesc = itemView.findViewById(R.id.ldescri);

        }
    }
}
