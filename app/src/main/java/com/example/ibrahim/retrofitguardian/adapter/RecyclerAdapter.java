package com.example.ibrahim.retrofitguardian.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibrahim.retrofitguardian.R;
import com.example.ibrahim.retrofitguardian.model.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Results> objects;

    public RecyclerAdapter(List<Results> objects) {
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Results results = objects.get(position);

        holder.sectionName.setText(results.getSectionName());
        holder.webTitle.setText(results.getWebTitle());
        holder.byline.setText(results.getFields().getByline());
        Picasso.get()
                .load(results.getFields().getThumbnail())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.no_image)
                .into(holder.thumbnail);
        holder.webPublicationDate.setText(results.getWebPublicationDate());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView sectionName;
        TextView webTitle;
        TextView byline;
        ImageView thumbnail;
        TextView webPublicationDate;

        public ViewHolder(View itemView) {
            super(itemView);

            sectionName = itemView.findViewById(R.id.section_name);
            webTitle = itemView.findViewById(R.id.web_title);
            byline = itemView.findViewById(R.id.byline);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            webPublicationDate = itemView.findViewById(R.id.web_publication_date);
        }
    }
}
