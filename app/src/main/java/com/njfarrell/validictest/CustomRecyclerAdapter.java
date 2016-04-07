package com.njfarrell.validictest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njfarrell.validictest.data.models.Item;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER_ITEM = 0;
    public static final int LIST_ITEM = 1;

    private List<Item> data;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_ITEM:
                return new HeaderViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.list_header, parent, false));
            case LIST_ITEM:
                return new ListViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                                R.layout.list_row, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            ((ListViewHolder)holder).setText(data.get(position).getText());
        } else {
            ((HeaderViewHolder)holder).setText(data.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null) {
            return HEADER_ITEM;
        }
        return data.get(position).getViewType();
    }

    /**
     * Update data and refresh UI for recycler view.
     * @param data list of data.
     */
    public void updateData(List<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(View listRow) {
            super(listRow);
        }

        public void setText(String text) {
            TextView textView = (TextView) itemView.findViewById(R.id.row_text);
            textView.setText(text);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View listRow) {
            super(listRow);
        }

        public void setText(String text) {
            TextView textView = (TextView) itemView.findViewById(R.id.header_text);
            textView.setText(text);
        }
    }
}
