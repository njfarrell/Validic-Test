package com.njfarrell.validictest.data;

import com.njfarrell.validictest.data.models.Item;

import java.util.List;

public class DataEvent {

    private final List<Item> data;

    public DataEvent(List<Item> data) {
        this.data = data;
    }

    public List<Item> getData() {
        return data;
    }
}
