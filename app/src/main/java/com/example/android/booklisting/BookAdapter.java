package com.example.android.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Elias on 29/05/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        ImageView bookImage = (ImageView) listItemView.findViewById(R.id.book_image_view);
        bookImage.setImageResource(currentBook.getmImage());

        TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title_view);
        bookTitle.setText(currentBook.getmTitle());

        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.book_author_view);
        bookAuthor.setText(currentBook.getmAuthor());

        return listItemView;
    }
}
