package com.vullnetlimani.myapplication.model;

public class PhoneModelListView {

    private int mPhoto;
    private String mTitle;

    public PhoneModelListView(int mPhoto, String mTitle) {
        this.mPhoto = mPhoto;
        this.mTitle = mTitle;
    }

    public int getmPhoto() {
        return mPhoto;
    }

    public String getmTitle() {
        return mTitle;
    }
}
