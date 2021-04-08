package com.vullnetlimani.myapplication.model;

public class RecyclerViewModel {

    private int photoID;
    private String mTitle;

    public RecyclerViewModel(int photoID, String mTitle) {
        this.photoID = photoID;
        this.mTitle = mTitle;
    }

    public int getPhotoID() {
        return photoID;
    }

    public String getmTitle() {
        return mTitle;
    }
}
