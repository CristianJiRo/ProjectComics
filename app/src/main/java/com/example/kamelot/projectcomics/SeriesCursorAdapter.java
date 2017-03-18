package com.example.kamelot.projectcomics;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.kamelot.projectcomics.databinding.SerieCeldaBinding;

/**
 * Created by Kamelot on 18/01/2017.
 */

public class SeriesCursorAdapter extends  CupBoardCursorAdapter<Serie> {


    public SeriesCursorAdapter(Context context, Class<Serie> entityClass) {
        super(context, entityClass);
    }

    @Override
    public View newView(Context context, Serie model, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        SerieCeldaBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.serie_celda, parent, false
        );

        return binding.getRoot();
    }

    @Override
    public void bindView(View view, Context context, Serie model) {
        SerieCeldaBinding binding = DataBindingUtil.getBinding(view);

        //Introducción de los datos.
        binding.tvTitle.setText(model.getName());
        binding.tvEpiTot.setText("0/"+model.getTotalepisodes());
        Glide.with(context).load(model.getImageThumb()).into(binding.ivPoster);

        if (model.getFav()==1){

            binding.ivFavoritos.setImageResource(R.drawable.ic_fav);
        }
        else {

            binding.ivFavoritos.setImageResource(R.drawable.ic_no_fav);
        }

    }
}
