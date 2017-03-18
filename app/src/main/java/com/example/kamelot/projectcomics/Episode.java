package com.example.kamelot.projectcomics;

import java.io.Serializable;

/**
 * Created by Kamelot on 07/01/2017.
 */

public class Episode implements Serializable {

    private String name;
    private String serie;
    private int serieID;
    private String imageThumb;
    private String number;
//    private int check;
//
//
//    public int getCheck() {return check;}
//
//    public void setChek(int fav) {this.check = fav;    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
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

    @Override
    public String toString() {
        return "Episode{" +
                "name='" + name + '\'' +
                ", serie='" + serie + '\'' +
                ", serieID=" + serieID +
                ", imageThumb='" + imageThumb + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
