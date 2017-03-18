package com.example.kamelot.projectcomics;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.kamelot.projectcomics.databinding.EpisodeCeldaBinding;
import com.example.kamelot.projectcomics.databinding.SerieCeldaBinding;

/**
 * Created by Kamelot on 24/02/2017.
 */

public class EpisodesCursosAdapter extends CupBoardCursorAdapter<Episode> {


    public EpisodesCursosAdapter (Context context, Class<Episode> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Episode model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        EpisodeCeldaBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.episode_celda, parent, false
        );

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Episode model) {
        EpisodeCeldaBinding binding = DataBindingUtil.getBinding(view);

        //Introducción de los datos.
        binding.tvEpisodeNumber.setText(model.getNumber());
        binding.tvNameEpisode.setText(model.getName());
        Glide.with(context).load(model.getImageThumb()).into(binding.ivEpisodePoster);

        if (model.getVista()==0) {

            binding.ivVisto.setImageResource(R.drawable.ic_no_vista);

        }
        else {

            binding.ivVisto.setImageResource(R.drawable.ic_vista);
        }

    }

}
