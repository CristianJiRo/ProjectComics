package com.example.kamelot.projectcomics;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kamelot on 05/01/2017.
 */

public class ApiCalls {

    private final String seriesURL =
            "https://comicvine.gamespot.com/api/series_list/?api_key=37953f46b5d8c2f9b10d8cd2d37b0861519a0d3d&sort=name:asc";

    private final String episodesURL =
            "https://comicvine.gamespot.com/api/episodes/?api_key=37953f46b5d8c2f9b10d8cd2d37b0861519a0d3d";

    //http://comicvine.gamespot.com/api/series_list/?api_key=37953f46b5d8c2f9b10d8cd2d37b0861519a0d3d&sort=name:asc&format=json

    //Procesado para las Series.

    ArrayList<Serie> getSeries(){

        Uri builtUri = Uri.parse(seriesURL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return doCallSerie(url);
    }
    @Nullable
    private ArrayList<Serie> doCallSerie (String url){
        ArrayList<Serie> series = new ArrayList<Serie>();
        try {

            //566
            for (int i = 0; i < 100 ; i+=100) {

                url += "&offset=" + String.valueOf(i) + "&format=json";
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Serie> pagina = processJsonSerie(JsonResponse);
                series.addAll(pagina);
                Log.d("Serie Page:", Integer.toString(i));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return series;
    }

    private ArrayList<Serie> processJsonSerie(String jsonResponse){

        ArrayList<Serie> series = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonSeries = data.getJSONArray("results");

            for (int i = 0; i < jsonSeries.length(); i++) {
                JSONObject jsonSerie = jsonSeries.getJSONObject(i);

                Serie serie = new Serie();

                //Metemos los datos sacados del json en nuestro objeto.
                serie.setImageThumb(jsonSerie.getJSONObject("image").getString("icon_url"));
                serie.setName(jsonSerie.getString("name"));
                serie.setTotalepisodes(jsonSerie.getString("count_of_episodes"));
                serie.setSerieID(jsonSerie.getInt("id"));


                //Hay muchos capitulos y bajarlos todos es una locura (me bloquea el PC), asi que fuerzo varias series como favoritas que tienen
                //episodios para poder comprobar las funcionalidades de forma comoda.

                if (serie.getSerieID() == 4 || serie.getSerieID() == 3 || serie.getSerieID() == 17 || serie.getSerieID() == 31
                        ||serie.getSerieID() == 65 || serie.getSerieID() == 32 || serie.getSerieID() == 54 ||
                        serie.getSerieID() == 28 )
                {
                    serie.setFav(1);
                }
                else {

                    serie.setFav(0);
                }

                serie.setDescription(jsonSerie.getString("description"));
                series.add(serie);

                Log.d(jsonSerie.getString("name"),Integer.toString(jsonSerie.getInt("id")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return series;
    }


    //Procesado para los episodios.

    ArrayList<Episode> getEpisodes(){


            Uri builtUri = Uri.parse(episodesURL)
                    .buildUpon()
                    .build();
            String url = builtUri.toString();

            return doCallEpisode(url);

    }

    @Nullable
    private ArrayList<Episode> doCallEpisode (String url){

        ArrayList<Episode> episodes = new ArrayList<>();
        try {

            //int pagTotales= 20814; //Bajarlos todos tarda demasiado...
            int pagTotales = 1600;
            for (int i = 0; i <pagTotales  ; i+=100) {
                url += "&offset=" + String.valueOf(i) + "&format=json";

                String JsonResponse = HttpUtils.get(url);
                ArrayList<Episode> pagina  = processJsonEpisode(JsonResponse);

                Log.d("Episode Page:", Integer.toString(i));
                episodes.addAll(pagina);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return episodes;
    }

    private ArrayList<Episode> processJsonEpisode(String jsonResponse){

        ArrayList<Episode> episodes = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);

            JSONArray jsonEpisodes = data.getJSONArray("results");
            for (int i = 0; i < jsonEpisodes.length(); i++) {
                JSONObject jsonEpisode = jsonEpisodes.getJSONObject(i);

                    Episode episode = new Episode();

                    //Metemos los datos sacados del json en nuestro objeto.

                    episode.setName(jsonEpisode.getString("name"));
                    episode.setImageThumb(jsonEpisode.getJSONObject("image").getString("icon_url"));
                    episode.setSerie(jsonEpisode.getJSONObject("series").getString("name"));
                    episode.setSerieID(jsonEpisode.getJSONObject("series").getInt("id"));
                    episode.setNumber(jsonEpisode.getString("episode_number"));
                    episode.setEpisodeID(jsonEpisode.getInt("id"));
                    episode.setVista(0);
                    episodes.add(episode);

                    Log.d("EPISODIO : "+ episode.getSerieID() +jsonEpisode.getString("name"), jsonEpisode.getJSONObject("series").getString("name"));

                }
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return episodes;
    }

}
