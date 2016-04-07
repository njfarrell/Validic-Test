package com.njfarrell.validictest.data;

public abstract class Item {

    private String text;

    public String getText() {
        if (text == null) {
            return "";
        }
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract int getViewType();
}
