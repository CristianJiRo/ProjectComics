package com.example.kamelot.projectcomics;

import android.net.Uri;
import android.support.annotation.Nullable;

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

    ArrayList<Serie> getSeries(){

        Uri builtUri = Uri.parse(seriesURL)
                .buildUpon()
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    @Nullable
    private ArrayList<Serie> doCall (String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Serie> processJson(String jsonResponse){

        ArrayList<Serie> series = new ArrayList<>();

        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonSeries = data.getJSONArray("results");
            for (int i = 0; i < jsonSeries.length(); i++) {
                JSONObject jsonSerie = jsonSeries.getJSONObject(i);

                Serie serie = new Serie();

                serie.setName(jsonSerie.getString("name"));
                serie.setTotalepisodes(jsonSerie.getString("count_of_episodes"));



                series.add(serie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return series;
    }
}
