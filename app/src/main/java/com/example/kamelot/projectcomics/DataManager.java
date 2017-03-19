package com.example.kamelot.projectcomics;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.net.Uri;
import android.util.Log;


import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by Kamelot on 18/01/2017.
 */

public class DataManager {

    private static UriHelper helper = UriHelper.with(ContentProvider.authority);

    private static Uri episodeUri = helper.getUri(Episode.class);
    private static Uri serieUri = helper.getUri(Serie.class);

    private static SharedPreferences preferences;

    static  void crearBD (ArrayList<Serie> resultSerie, ArrayList<Episode> resultEpisode, Context context){

        cupboard().withContext(context).put(episodeUri, Episode.class, resultEpisode);
        cupboard().withContext(context).put(serieUri, Serie.class, resultSerie);


        Log.d("Debug Serie", resultSerie.toString());
        Log.d("Debug Episode", resultEpisode.toString());

    }

    static void deleteBD (Context context){

        cupboard().withContext(context).delete(episodeUri, "_id >?", "0");
        cupboard().withContext(context).delete(serieUri, "_id >?", "0");
    }

    static void obtenerBD (Context context){

        cupboard().withContext(context).get(episodeUri, Episode.class);
        cupboard().withContext(context).get(serieUri, Serie.class);
    }

    static void updateSerie (Context context, String id, int fav){
        ContentValues values = new ContentValues(1);
        values.put("fav", fav);

        cupboard().withContext(context).update(serieUri, values, "serieID = ?", id );
    }

    static void updateEpisode (Context context, String id, int vista){
        ContentValues values = new ContentValues(1);
        values.put("vista", vista);
        cupboard().withContext(context).update(episodeUri, values, "episodeID = ?", id);
    }

    static CursorLoader getCursorLoader (Context context, int option) {

//        0 = Episodios de la serie seleccionada.
//        1 = Todas las series.
//        2 = Series Favoritas.

        if (option == 0) {

            preferences = PreferenceManager.getDefaultSharedPreferences(context);

            Log.d("selected_id----------", preferences.getString("selected_id", "8"));

            String idSerie = preferences.getString("selected_id", "8");
            String selection = "( serieID =?)";
            String[] selectionArgs = new String[1];
            selectionArgs[0] = idSerie;

            return new CursorLoader(context, episodeUri, null, selection, selectionArgs, null);
        }
        else if (option == 1) {

            return new CursorLoader(context, serieUri, null, null, null, null);
        }
        else {

            String sel = "( fav =?)";
            String[] selArgs = new String[1];
            selArgs[0] = "1";

            return new CursorLoader(context, serieUri, null, sel, selArgs, null);
        }

    }

}
