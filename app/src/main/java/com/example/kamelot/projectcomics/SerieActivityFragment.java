package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SerieActivityFragment extends Fragment {

    private View view;
    private ImageView ivSerieDetail;
    private TextView tvSerieName;
    private TextView tvDescription;
    private TextView tvEpisodesCount;
    private ListView lvEpisodes;
    private int serieID;


    private ArrayList<Episode> items;
    private CustomAdapterEpisodes adapter;

    public SerieActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_serie, container, false);

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


        ivSerieDetail = (ImageView) view.findViewById(R.id.ivSerieDetail);
        tvSerieName = (TextView) view.findViewById(R.id.tvSerieName);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvEpisodesCount = (TextView) view.findViewById(R.id.tvEpisodesCount);

        lvEpisodes =(ListView) view.findViewById(R.id.lvEpisodes);

        items = new ArrayList<>();
        adapter = new CustomAdapterEpisodes(
                getContext(),
                R.layout.episode_celda,
                items
        );

        lvEpisodes.setAdapter(adapter);


        Glide.with(getContext()).load(serie.getImageThumb()).into(ivSerieDetail);
        tvSerieName.setText(serie.getName());
        //Para quitar las etiquetas HTML, el metodo esta deprecated, pero la aternativa solo funciona en Apis superiores a esta.
        String descripcion = Html.fromHtml(serie.getDescription()).toString();
        tvDescription.setText(descripcion);
        tvDescription.setMovementMethod(new ScrollingMovementMethod());

        tvEpisodesCount.setText("0/"+serie.getTotalepisodes());

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
