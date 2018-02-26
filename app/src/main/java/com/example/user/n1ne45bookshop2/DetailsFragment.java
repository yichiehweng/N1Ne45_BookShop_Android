package com.example.user.n1ne45bookshop2;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class DetailsFragment extends Fragment {
    final static int []view = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7};
    final static String []key = {"BookID", "Title", "CategoryID", "ISBN","Author","Price","Discount"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.detailsfragment, container, false);
        Bundle arg = getArguments();
        if (arg != null) {
            String item = arg.getString("Id");
            String isbn = arg.getString("ISBN");
            new AsyncTask<String, Void, Book>() {
                @Override
                protected Book doInBackground(String... params) {
                    return Book.getBook(params[0]);
                }
                @Override
                protected void onPostExecute(final Book result) {
                    for (int i=0; i<view.length; i++) {
                        TextView t = (TextView) v.findViewById(view[i]);
                        t.setText(result.get(key[i]));
                    }
                }
            }.execute(item);
            final ImageView image = v.findViewById(R.id.imageView);
            new AsyncTask<String, Void, Bitmap>(){
                @Override
                protected Bitmap doInBackground(String... params) {
                    return Book.getPhoto(params[0]);
                }
                @Override
                protected void onPostExecute(final Bitmap photo){
                    image.setImageBitmap(photo);
                }
            }.execute(isbn);
        }
        return(v);
        }

    }

