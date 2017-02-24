package com.example.kamelot.projectcomics;

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

    static CursorLoader getCursorLoader (Context context, Boolean episode) {

        if(episode){

            String selection = "( serieID =?)";
            String [] selectionArgs = new String[1];
            selectionArgs[0] = "8";

            return new CursorLoader(context, episodeUri, null, selection, selectionArgs,  null );
        }
        else {

            return new CursorLoader(context, serieUri, null, null, null, null );
        }


    }

}