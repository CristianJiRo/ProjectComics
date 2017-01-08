package com.example.kamelot.projectcomics;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.Html;

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
            "http://comicvine.gamespot.com/api/series_list/?api_key=37953f46b5d8c2f9b10d8cd2d37b0861519a0d3d&limit=5&format=json";

    private final String episodesURL =
            "http://comicvine.gamespot.com/api/episodes/?api_key=37953f46b5d8c2f9b10d8cd2d37b0861519a0d3d&limit=5&format=json";


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
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJsonSerie(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

                serie.setDescription(jsonSerie.getString("description"));

                series.add(serie);
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
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJsonEpisode(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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

                episodes.add(episode);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return episodes;
    }

}
