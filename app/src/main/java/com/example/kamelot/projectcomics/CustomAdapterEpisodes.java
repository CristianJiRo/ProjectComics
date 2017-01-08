package com.example.kamelot.projectcomics;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Kamelot on 06/01/2017.
 */

public class CustomAdapterEpisodes extends ArrayAdapter<Episode>{

    public CustomAdapterEpisodes(Context context, int resource, ArrayList<Episode> objects) {
        super(context, resource, objects);

    }

    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Episode episode = getItem(position);

        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.episode_celda, parent, false);

        }

        //Enlaces con los componentes del layout.
        TextView tvTitleEpisode = (TextView) convertView.findViewById(R.id.tvNameEpisode);
        TextView tvEpisodeNumber = (TextView) convertView.findViewById(R.id.tvEpisodeNumber);
        ImageView ivEpisodePoster = (ImageView) convertView.findViewById(R.id.ivEpisodePoster);


        //Introducción de los datos.
        tvTitleEpisode.setText(episode.getName());
        Glide.with(getContext()).load(episode.getImageThumb()).into(ivEpisodePoster);
        tvEpisodeNumber.setText(episode.getNumber());

        return convertView;
    }
}
