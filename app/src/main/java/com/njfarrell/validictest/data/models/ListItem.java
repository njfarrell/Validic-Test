package com.njfarrell.validictest.data.models;

import com.njfarrell.validictest.CustomRecyclerAdapter;

public class ListItem extends Item {

    public ListItem(String trend) {
        setText(trend);
    }

    @Override
    public int getViewType() {
        return CustomRecyclerAdapter.LIST_ITEM;
    }
}
