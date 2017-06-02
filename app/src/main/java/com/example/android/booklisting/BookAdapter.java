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

import org.json.JSONException;

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

        ViewHolderItem viewHolder;

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.bookImage = (ImageView) listItemView.findViewById(R.id.book_image_view);
            viewHolder.bookTitle = (TextView) listItemView.findViewById(R.id.book_title_view);
            viewHolder.bookAuthor = (TextView) listItemView.findViewById(R.id.book_author_view);

            listItemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) listItemView.getTag();
        }

        Book currentBook = getItem(position);

        viewHolder.bookImage.setImageBitmap(currentBook.getmImage());

        viewHolder.bookTitle.setText(currentBook.getmTitle());

        try {
            viewHolder.bookAuthor.setText(currentBook.getmAuthor().join("; ").replace("\"", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listItemView;
    }

    static class ViewHolderItem {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
    }
}
