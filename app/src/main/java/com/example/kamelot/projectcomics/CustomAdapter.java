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

public class CustomAdapter extends ArrayAdapter<Serie>{

    public CustomAdapter(Context context, int resource, ArrayList<Serie> objects) {
        super(context, resource, objects);

    }

    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Serie serie = getItem(position);

        if (convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.serie_celda, parent, false);

        }

        //Enlaces con los componentes del layout.
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvEpiTot = (TextView) convertView.findViewById(R.id.tvEpiTot);
        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);


        //Introducción de los datos.
        tvTitle.setText(serie.getName());
        tvEpiTot.setText("0/"+serie.getTotalepisodes());
        Glide.with(getContext()).load(serie.getImageThumb()).into(ivPoster);

        return convertView;
    }
}
