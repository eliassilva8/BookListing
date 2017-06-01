package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int BOOK_LOADER_ID = 1;
    private BookAdapter mAdapter;
    private TextView mEmptyTextView;
    private String userInput;
    private View progressView;
    private LoaderManager loaderManager;
    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        final ListView listView = (ListView) findViewById(R.id.list_view);

        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyTextView);
        mEmptyTextView.setText(R.string.open_view);

        progressView = findViewById(R.id.progress_view);
        progressView.setVisibility(View.GONE);

        listView.setAdapter(mAdapter);

        connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
        if (!(networkInfo != null && networkInfo.isConnected())) {
            mEmptyTextView.setText(R.string.no_internet_connection);
        }

        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.clear();
                userInput = query;
                connectivityManager();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, REQUEST_URL + userInput);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        progressView.setVisibility(View.GONE);

        mEmptyTextView.setText(R.string.no_books);

        mAdapter.clear();

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }

    private void connectivityManager() {
        networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager = getLoaderManager();
            loaderManager.restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
            progressView.setVisibility(View.VISIBLE);
        } else {
            progressView.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet_connection);
        }
    }
}
