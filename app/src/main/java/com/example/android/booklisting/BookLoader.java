package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Elias on 30/05/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private String mUserInput;

    public BookLoader(Context context, String userInput) {
        super(context);
        mUserInput = userInput;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {

        List<Book> books = QueryUtils.fetchBookData(REQUEST_URL + mUserInput);
        return books;
    }
}
