package com.example.android.booklisting;

import android.graphics.Bitmap;

import org.json.JSONArray;

/**
 * Created by Elias on 29/05/2017.
 */

public class Book {
    private Bitmap mImage;
    private String mTitle;
    private JSONArray mAuthor;

    public Bitmap getmImage() {
        return mImage;
    }

    public Book(String mTitle, JSONArray mAuthor, Bitmap mImage) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public JSONArray getmAuthor() {
        return mAuthor;
    }
}
