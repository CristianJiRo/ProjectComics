package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.kamelot.projectcomics.databinding.FragmentSerieBinding;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SerieActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private int serieID;
    private FragmentSerieBinding binding;
    private DataManager dm;
    private Serie serie;

    private EpisodesCursosAdapter adapter;

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
            serie = (Serie) i.getSerializableExtra("serie");

            if (serie != null){

                updateUi(serie);
            }
        }

        binding.lvEpisodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Episode episode = (Episode) parent.getItemAtPosition(position);

                if (episode.getVista()==0){

                    episode.setVista(1);
                }
                else {

                    episode.setVista(0);
                }

                dm.updateEpisode(getContext(),Integer.toString(episode.getEpisodeID()) , episode.getVista());
            }
        });


        return view;
    }

    private void updateUi(final Serie serie){

        adapter = new EpisodesCursosAdapter(getContext(),Episode.class);

        binding.lvEpisodes.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        Glide.with(getContext()).load(serie.getImageThumb()).into(binding.ivSerieDetail);
        binding.tvSerieName.setText(serie.getName());

        //Para quitar las etiquetas HTML, el metodo esta deprecated, pero la aternativa solo funciona en Apis superiores a esta.
        String descripcion = Html.fromHtml(serie.getDescription()).toString();
        binding.tvDescription.setText(descripcion);
        binding.tvDescription.setMovementMethod(new ScrollingMovementMethod());
        binding.tvEpisodesCount.setText("0/"+serie.getTotalepisodes());
        serieID = serie.getSerieID();
        String ID = Integer.toString(serieID);
        Log.d("ID---------------------", ID);

        if (serie.getFav() ==1){

            binding.ibFav.setImageResource(R.drawable.ic_fav);
        }
        else {

            binding.ibFav.setImageResource(R.drawable.ic_no_fav);
        }

        binding.ibFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (serie.getFav()==0){
                    serie.setFav(1);
                }
                else {

                    serie.setFav(0);

                }

                Log.d("Favorito:    ", Integer.toString(serie.getFav()));

                dm.updateSerie(getContext(),Integer.toString(serie.getSerieID()) , serie.getFav());

                if (serie.getFav() ==1){

                    binding.ibFav.setImageResource(R.drawable.ic_fav);
                }
                else {

                    binding.ibFav.setImageResource(R.drawable.ic_no_fav);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext(),true);
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
