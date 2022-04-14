package com.example.zwiggy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zwiggy.R;
import com.example.zwiggy.model.FeaturesApp;

import java.util.List;

public class FeaturesAppAdapter extends RecyclerView.Adapter<FeaturesAppAdapter.ViewHolder>{

    Context context;
    List<FeaturesApp> featuresAppList;

    public FeaturesAppAdapter(Context context, List<FeaturesApp> featuresAppList) {
        this.context = context;
        this.featuresAppList = featuresAppList;
    }

    @NonNull
    @Override
    public FeaturesAppAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.features_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturesAppAdapter.ViewHolder holder, int position) {

        holder.featurestext.setText(featuresAppList.get(position).getFeaturestext());
    }

    @Override
    public int getItemCount() {
        return featuresAppList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView featurestext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            featurestext=itemView.findViewById(R.id.featurestext);

        }
    }
}
