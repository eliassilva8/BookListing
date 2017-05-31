package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private BookAdapter mAdapter;
    private TextView mEmptyTextView;
    private static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android";
    private String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        final ListView listView = (ListView) findViewById(R.id.list_view);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyTextView);

        listView.setAdapter(mAdapter);

        final LoaderManager loaderManager = getLoaderManager();

        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                loaderManager.initLoader(1, null, MainActivity.this);
                userInput = query;
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
        return new BookLoader(this, REQUEST_URL);
    }


    //TODO concluir metodo
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View progressView = findViewById(R.id.progress_view);
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
}
