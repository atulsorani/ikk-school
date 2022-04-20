package com.atul.schoolmgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class quotesAdapter extends RecyclerView.Adapter<quotesAdapter.quotesHolder> {

    private Context mCtx;
    private List<model_quotes> quotesList;

    public quotesAdapter(Context mCtx, List<model_quotes> quotesList) {
        this.mCtx = mCtx;
        this.quotesList = quotesList;
    }

    @Override
    public quotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(mCtx);
        View view = lyf.inflate(R.layout.quoteslayout, null);
        return new quotesHolder(view);
    }

    @Override
    public void onBindViewHolder(quotesHolder holder, int position) {
        model_quotes quo = quotesList.get(position);
        holder.textViewdate.setText(quo.getDate());
        //holder.textViewQuotes.setText(quo.getQuotes());
        Glide.with(mCtx).load(quotesList.get(position).getQuotes()).into(holder.textViewQuotes);
        holder.textdesc.setText(quo.getDesc());

    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    class quotesHolder extends RecyclerView.ViewHolder {

        TextView textViewdate,textdesc;
        ImageView textViewQuotes;

        public quotesHolder(View itemView) {
            super(itemView);
            textViewdate = itemView.findViewById(R.id.qdate);
            textViewQuotes = itemView.findViewById(R.id.qdescri);
            textdesc = itemView.findViewById(R.id.description);

        }
    }
}
