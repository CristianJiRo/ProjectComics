package com.example.kamelot.projectcomics;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class SerieActivityFragment extends Fragment {

    private View view;
    private ImageView ivSerieDetail;
    private TextView tvSerieName;
    private TextView tvDescription;
    private TextView tvEpisodesCount;

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


        Glide.with(getContext()).load(serie.getImageThumb()).into(ivSerieDetail);
        tvSerieName.setText(serie.getName());
        //Para quitar las etiquetas HTML, el metodo esta deprecated, pero la aternativa solo funciona en Apis superiores a esta.
        String descripcion = Html.fromHtml(serie.getDescription()).toString();
        tvDescription.setText(descripcion);
        tvDescription.setMovementMethod(new ScrollingMovementMethod());

        tvEpisodesCount.setText("0/"+serie.getTotalepisodes());

    }
}
