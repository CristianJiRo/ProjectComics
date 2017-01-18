package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.kamelot.projectcomics.databinding.FragmentMainBinding;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private SeriesCursorAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);


        View view = binding.getRoot();


        adapter = new SeriesCursorAdapter(getContext(),Serie.class);

        binding.lvSeries.setAdapter(adapter);
        binding.lvSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Serie serie = (Serie) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), SerieActivity.class);
                intent.putExtra("serie", serie);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(0, null, this);

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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext(), false);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {

            ApiCalls api = new ApiCalls();

            ArrayList<Episode> resultEpisode =api.getEpisodes();
            ArrayList<Serie> resultSerie = api.getSeries();

            DataManager.deleteBD(getContext());
            DataManager.crearBD(resultSerie, resultEpisode, getContext());

            return null;
        }


    }


}
