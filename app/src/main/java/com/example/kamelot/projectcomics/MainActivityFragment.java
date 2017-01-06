package com.example.kamelot.projectcomics;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Serie> items;
    private CustomAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        ListView lvSeries = (ListView) view.findViewById(R.id.lvSeries);


        items = new ArrayList<>();
        adapter = new CustomAdapter(
                getContext(),
                R.layout.serie_celda,
                items
        );

        lvSeries.setAdapter(adapter);


        return view;
    }

    private void refresh(){

        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Serie>>{
        @Override
        protected ArrayList<Serie> doInBackground(Void... voids) {

            ApiCalls api = new ApiCalls();
            ArrayList<Serie> result = api.getSeries();
            Log.d("Debug", result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Serie> series) {
            adapter.clear();
            for (Serie serie: series){

                adapter.add(serie);
            }
        }
    }

}
