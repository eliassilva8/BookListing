package com.example.android.booklisting;

/**
 * Created by Elias on 29/05/2017.
 */

public class Book {
    //private Uri mImage;
    private String mTitle;
    //private String mAuthor;

    public Book(String mTitle) {
        //this.mImage = mImage;
        this.mTitle = mTitle;
        //this.mAuthor = mAuthor;
    }

   /* public Uri getmImage() {
        return mImage;
    }*/

    public String getmTitle() {
        return mTitle;
    }

    /*public String getmAuthor() {
        return mAuthor;
    }*/
}
