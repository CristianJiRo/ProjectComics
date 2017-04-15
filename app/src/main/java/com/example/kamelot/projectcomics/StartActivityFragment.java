package com.example.kamelot.projectcomics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kamelot.projectcomics.databinding.FragmentStartBinding;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private SeriesCursorAdapter adapter;
    private SharedPreferences preferences;
    private ProgressDialog dialog;
    private DataManager dm = new DataManager();
    private FragmentStartBinding binding;

    public StartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_start, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor editor = preferences.edit();

        View view = binding.getRoot();

        getLoaderManager().initLoader(0, null, this);

        if (preferences.getBoolean("first_time", true)){

            editor.putBoolean("first_time", false).commit();
            dialog = new ProgressDialog(getContext());
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMessage("Loading...");
            Log.d("---------------------", "BD creada");
            refresh();

        }
        else {


            dm.obtenerBD(getContext());
            Log.d("Recuperacion de datos", "");

        }





        binding.btSeries0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        binding.btFav0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), FavoritasActivity.class);
                startActivityForResult(intent, 0);
            }
        });



        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }

    private void refresh(){

        StartActivityFragment.RefreshDataTask task = new StartActivityFragment.RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ApiCalls api = new ApiCalls();

            ArrayList<Episode> resultEpisode =api.getEpisodes();
            ArrayList<Serie> resultSerie = api.getSeries();
            DataManager.crearBD(resultSerie, resultEpisode, getContext());

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }
}
