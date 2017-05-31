package com.example.android.booklisting;

/**
 * Created by Elias on 29/05/2017.
 */

public class Book {
    private int mImage;
    private String mTitle;
    private String mAuthor;

    public Book(int mImage, String mTitle, String mAuthor) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }
}
