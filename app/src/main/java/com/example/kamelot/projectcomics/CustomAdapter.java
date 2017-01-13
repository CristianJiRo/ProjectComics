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
import com.example.kamelot.projectcomics.databinding.SerieCeldaBinding;

/**
 * Created by Kamelot on 06/01/2017.
 */

public class CustomAdapter extends ArrayAdapter<Serie>{

    public CustomAdapter(Context context, int resource, ArrayList<Serie> objects) {
        super(context, resource, objects);

    }

    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Serie serie = getItem(position);

        SerieCeldaBinding binding = null;


        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater,R.layout.serie_celda, parent, false);

        }
        else {

            binding = DataBindingUtil.getBinding(convertView);
        }

        //Introducción de los datos.
        binding.tvTitle.setText(serie.getName());
        binding.tvEpiTot.setText("0/"+serie.getTotalepisodes());
        Glide.with(getContext()).load(serie.getImageThumb()).into(binding.ivPoster);

        return binding.getRoot();
    }
}
