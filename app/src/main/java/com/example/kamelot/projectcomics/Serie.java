package com.example.kamelot.projectcomics;

/**
 * Created by Kamelot on 05/01/2017.
 */

public class Serie {

    private String totalepisodes;
    private String name;
    private String imageThumb;

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
