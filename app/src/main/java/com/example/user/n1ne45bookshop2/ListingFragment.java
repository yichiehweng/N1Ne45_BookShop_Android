package com.example.user.n1ne45bookshop2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class ListingFragment extends ListFragment {
    final String TAG = "CATEGORY_FRAG";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list, container, false);
        Bundle arg = getArguments();
        if (arg != null) {
            String cat = arg.getString("cat");
            new AsyncTask<String, Void, List<Book>>() {
                @Override
                protected List<Book> doInBackground(String... params) {
                    return Book.listBookByCategory(params[0]);
                }
                @Override
                protected void onPostExecute(List<Book> result) {
                    SimpleAdapter adapter = new SimpleAdapter(getActivity() ,result ,
                            android.R.layout.simple_list_item_2,
                            new String[]{"Title", "Author"},
                            new int[]{android.R.id.text1, android.R.id.text2});
                    setListAdapter(adapter);
                }
            }.execute(cat);
        }
        else{
            new AsyncTask<Void, Void, List<Book>>() {
                @Override
                protected List<Book> doInBackground(Void... params) {
                    return Book.listBook();
                }
                @Override
                protected void onPostExecute(List<Book> result) {
                    SimpleAdapter adapter = new SimpleAdapter(getActivity() ,result ,
                            android.R.layout.simple_list_item_2,
                            new String[]{"Title", "Author"},
                            new int[]{android.R.id.text1, android.R.id.text2});
                    setListAdapter(adapter);
                }
            }.execute();
        }
        return(v);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Book theBook = (Book) getListAdapter().getItem(position);
        String theBookID = theBook.get("BookID");
        String theISBN = theBook.get("ISBN");

        if (getActivity().findViewById(R.id.detailsframe) == null) {
            // single-pane
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            Bundle extras = new Bundle();
            extras.putString("Id",theBook.get("BookID"));
            extras.putString("ISBN",theBook.get("ISBN"));
            intent.putExtras(extras);
            startActivity(intent);
        } else
            display(theBookID,theISBN );
    }
    void display(String theBookID, String theISBN) {
        final String TAG = "DETAILS_FRAG";
        FragmentManager fm = getFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Fragment frag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("Id", theBookID);
        args.putString("ISBN", theISBN);
        frag.setArguments(args);
        if (fm.findFragmentByTag(TAG) == null)
            trans.add(R.id.detailsframe, frag, TAG);
        else
            trans.replace(R.id.detailsframe, frag, TAG);
        trans.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.option1:{
                final String TAG1 = "CATEGORY_FRAG1";
                FragmentManager fm = getFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                Fragment fragment = fm.findFragmentByTag("CATEGORY_FRAG");
                trans.remove(fragment);
                Fragment newFragment=new ListingFragment();
                Bundle args = new Bundle();
                args.putString("cat", "1");
                fragment.setArguments(args);
                if (fm.findFragmentByTag(TAG) == null)
                    trans.add(R.id.fragment1, newFragment, TAG1);
                else
                    trans.replace(R.id.fragment1, newFragment, TAG1);
                trans.commit();
                return true;
            }

            case R.id.option2:{
                //final String TAG = "CATEGORY_FRAG";
                FragmentManager fm = getFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                Fragment fragment = fm.findFragmentByTag("CATEGORY_FRAG");
                Bundle args = new Bundle();
                args.putString("cat", "2");
                fragment.setArguments(args);
                if (fm.findFragmentByTag(TAG) == null)
                    // fragment not found -- to be added
                    trans.add(R.id.fragment1, fragment, TAG);
                else
                    // fragment found -- to be replaced
                    trans.replace(R.id.fragment1, fragment, TAG);
                trans.commit();
                return true;
            }

            case R.id.option3:{
                //final String TAG = "CATEGORY_FRAG";
                FragmentManager fm = getFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                Fragment fragment = fm.findFragmentByTag("CATEGORY_FRAG");
                Bundle args = new Bundle();
                args.putString("cat", "3");
                fragment.setArguments(args);
                if (fm.findFragmentByTag(TAG) == null)
                    // fragment not found -- to be added
                    trans.add(R.id.fragment1, fragment, TAG);
                else
                    // fragment found -- to be replaced
                    trans.replace(R.id.fragment1, fragment, TAG);
                trans.commit();
                return true;
            }
            case R.id.option4:{
                //final String TAG = "CATEGORY_FRAG";
                FragmentManager fm = getFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();

                Fragment fragment = fm.findFragmentByTag("CATEGORY_FRAG");
                Bundle args = new Bundle();
                args.putString("cat", "4");
                fragment.setArguments(args);
                if (fm.findFragmentByTag(TAG) == null)
                    // fragment not found -- to be added
                    trans.add(R.id.fragment1, fragment, TAG);
                else
                    // fragment found -- to be replaced
                    trans.replace(R.id.fragment1, fragment, TAG);
                trans.commit();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
