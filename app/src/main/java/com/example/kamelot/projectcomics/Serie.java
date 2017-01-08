package com.example.kamelot.projectcomics;

import java.io.Serializable;

/**
 * Created by Kamelot on 05/01/2017.
 */

public class Serie implements Serializable {

    private String name;
    private String totalepisodes;
    private String imageThumb;
    private int serieID;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSerieID() {
        return serieID;
    }

    public void setSerieID(int serieID) {
        this.serieID = serieID;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getTotalepisodes() {
        return totalepisodes;
    }

    public void setTotalepisodes(String totalepisodes) {
        this.totalepisodes = totalepisodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "Episodios totales= '" + totalepisodes + '\'' +
                ", Nombre= '" + name + '\'' +
                '}';
    }
}
