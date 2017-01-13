package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import com.example.kamelot.projectcomics.databinding.FragmentMainBinding;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Serie> items;
    private CustomAdapter adapter;
    private FragmentMainBinding binding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);


        View view = binding.getRoot();

        items = new ArrayList<>();
        adapter = new CustomAdapter(
                getContext(),
                R.layout.serie_celda,
                items
        );

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
