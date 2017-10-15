package com.cse.mist.bookstack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 8/18/2017.
 */

public class BookAdapter extends ArrayAdapter<Books> {
    List<Books> bookList;
    private Activity context;

    public BookAdapter(Activity context, List<Books> bookList) {
        super(context, R.layout.item_book_list, bookList);

        this.context = context;
        this.bookList = bookList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.item_book_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewNamew = (TextView) listViewItem.findViewById(R.id.textViewNamew);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);
        TextView textViewPay = (TextView) listViewItem.findViewById(R.id.textViewPay);


        Books temp = bookList.get(position);

        textViewName.setText(temp.getBookName());
        textViewGenre.setText(temp.getBookGenre());
        textViewNamew.setText(temp.getWriter());
        textViewPay.setText(temp.getType());

        return listViewItem;

    }
}
