package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.kamelot.projectcomics.databinding.FragmentSerieBinding;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SerieActivityFragment extends Fragment {

      private int serieID;
    private FragmentSerieBinding binding;


    private ArrayList<Episode> items;
    private CustomAdapterEpisodes adapter;

    public SerieActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(
                inflater, R.layout.fragment_serie, container, false);

        View view = binding.getRoot();



        Intent i = getActivity().getIntent();

        if (i !=null){
            Serie serie = (Serie) i.getSerializableExtra("serie");

            if (serie != null){

                updateUi(serie);
            }
        }


        return view;
    }

    private void updateUi(Serie serie){

        items = new ArrayList<>();
        adapter = new CustomAdapterEpisodes(
                getContext(),
                R.layout.episode_celda,
                items
        );

        binding.lvEpisodes.setAdapter(adapter);

        Glide.with(getContext()).load(serie.getImageThumb()).into(binding.ivSerieDetail);
        binding.tvSerieName.setText(serie.getName());

        //Para quitar las etiquetas HTML, el metodo esta deprecated, pero la aternativa solo funciona en Apis superiores a esta.
        String descripcion = Html.fromHtml(serie.getDescription()).toString();
        binding.tvDescription.setText(descripcion);
        binding.tvDescription.setMovementMethod(new ScrollingMovementMethod());

        binding.tvEpisodesCount.setText("0/"+serie.getTotalepisodes());

        serieID = serie.getSerieID();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh(){

        SerieActivityFragment.RefreshDataTaskEpisode task = new RefreshDataTaskEpisode();
        task.execute();
    }

    private class RefreshDataTaskEpisode extends AsyncTask<Void, Void, ArrayList<Episode>> {
        @Override
        protected ArrayList<Episode> doInBackground(Void... voids) {

            ApiCalls api = new ApiCalls();
            ArrayList<Episode> result = api.getEpisodes(serieID);
            Log.d("Debug", result.toString());

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Episode> episodes) {
            adapter.clear();
            for (Episode episode: episodes){

                adapter.add(episode);
            }
        }
    }

}
