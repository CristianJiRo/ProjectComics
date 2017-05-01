package com.example.kamelot.projectcomics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
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
    private SharedPreferences preferences;
    private ProgressDialog dialog;
    private DataManager dm = new DataManager();

    private FragmentMainBinding binding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor editor = preferences.edit();

        View view = binding.getRoot();

        Log.d("Dibujado", "XXXXXXx");

        getLoaderManager().initLoader(0, null, this);

        adapter = new SeriesCursorAdapter(getContext(),Serie.class);

        binding.lvSeries.setAdapter(adapter);

        binding.lvSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Serie serie = (Serie) adapterView.getItemAtPosition(i);

                editor.putString("selected_id", Integer.toString(serie.getSerieID()));
                editor.commit();

//                Log.d("selected_id----------",preferences.getString("selected_id", "1"));

                Intent intent = new Intent(getContext(), SerieActivity.class);
                intent.putExtra("serie", serie);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dm.obtenerBD(getContext());
        binding.lvSeries.invalidateViews();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext(), 1);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }
}
