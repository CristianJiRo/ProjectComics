package com.example.kamelot.projectcomics;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import com.example.kamelot.projectcomics.databinding.EpisodeCeldaBinding;

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

        EpisodeCeldaBinding binding = null;

        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.episode_celda, parent, false);

        }
        else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        //Introducción de los datos.
        binding.tvNameEpisode.setText(episode.getName());
        Glide.with(getContext()).load(episode.getImageThumb()).into(binding.ivEpisodePoster);
        binding.tvEpisodeNumber.setText(episode.getNumber());

        return binding.getRoot();
    }
}
