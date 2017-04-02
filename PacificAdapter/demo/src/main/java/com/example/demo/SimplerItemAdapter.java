package com.example.demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimplerItemAdapter extends RecyclerView.Adapter<SimplerItemAdapter.SimpleItemViewHolder> {

    private List<String> items;

    public SimplerItemAdapter(@NonNull List<String> dateItems) {
        this.items = (dateItems != null ? dateItems : new ArrayList<String>());
    }

    @Override public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override public void onBindViewHolder(SimpleItemViewHolder viewHolder, int position) {
        viewHolder.textView.setText(items.get(position));
    }

    @Override public int getItemCount() {
        return (this.items != null) ? this.items.size() : 0;
    }

    protected final static class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
