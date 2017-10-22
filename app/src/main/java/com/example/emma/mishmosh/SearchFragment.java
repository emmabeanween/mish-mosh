package com.example.emma.mishmosh;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emma on 7/13/17.
 */

public class SearchFragment extends android.support.v4.app.DialogFragment {

    private ArrayAdapter<String> mArrayAdapter;
    private ListView mListView;
    private SearchView mSearchView;
    String selected  = "";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.search_fragment, null);




        // set up fragment


        String cities[] = {"New York", "Dallas", "Seattle", "Sacramento", "San Francisco", "Los Angeles"
                , "Houston", "Boston", "Detroit", "Philadelphia", "Washington D.C", "Indianapolis",
                "Minneapolis", "Baltimore", "San Antonio", "Austin", "Denver", "San Diego", "Memphis", "Nashville",
                "Phoenix", "Atlanta", "San Jose", "Kansas City", "Portland", "New Orleans", "Milwaukee",
                "Raleigh", "Miami", "Fort Worth", "Cincinatti", "Cleveland", "Columbus", "Jacksonville", "Orlando"
                , "Oklahmoma City", "Charlotte", "Pittsburgh", "Honolulu", "Tampa", "Omaha", "Tuscon", "El Paso"
                , "Oakland", "Virginia Beach", "St. Louis", "Birmingham", "Las Vegas"};
        // set up adapter

        mArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, cities);


        // find listview and populate it with cities

        mListView = (ListView) v.findViewById(R.id.my_list);
        mListView.setAdapter(mArrayAdapter);


        // find searchview and get the text

        mSearchView = (SearchView) v.findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // get the text from the searchview and filter adapter and thus listview results based on what's searched
                mArrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String itemselected = mListView.getItemAtPosition(i).toString();
                selected = itemselected;
            }
        });

        ///




        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                             // get the user selected item

                                Intent intent = new Intent();
                                intent.putExtra("city", selected);
                                Log.i("TAG", selected);




                                getTargetFragment()
                                        .onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);







                    }
                })
                .create();


    }


    public static String getCity(Intent dat){


        return dat.getStringExtra("city");
    }

}









