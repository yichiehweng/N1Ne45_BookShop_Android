package com.example.user.n1ne45bookshop2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

public class DetailsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsfragment);
        Intent intent = getIntent();

        if (intent.hasExtra("Id")) {
            String item = getIntent().getStringExtra("Id");
            String isbn=getIntent().getStringExtra("ISBN");
            Bundle args = new Bundle();
            args.putString("Id", item);
            args.putString("ISBN", isbn);
            Fragment f = new DetailsFragment();
            f.setArguments(args);
            getFragmentManager().beginTransaction()
                    .add(R.id.detailsframe, f)
                    .commit();
        }
    }
}
