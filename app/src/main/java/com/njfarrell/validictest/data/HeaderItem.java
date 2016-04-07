package com.njfarrell.validictest.data;

import com.njfarrell.validictest.CustomRecyclerAdapter;

public class HeaderItem extends Item {

    public HeaderItem(String location) {
        setText(location);
    }

    @Override
    public int getViewType() {
        return CustomRecyclerAdapter.HEADER_ITEM;
    }
}
