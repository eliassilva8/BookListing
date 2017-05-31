package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Elias on 30/05/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private static final String LOG_TAG = BookLoader.class.getName();
    private String mUrl;

    public BookLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {

        URL url = QueryUtils.createUrl("https://www.googleapis.com/books/v1/volumes?q=query");
        try {
            Log.v("#####Loader####", QueryUtils.makeHttpRequest(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
