package com.example.kamelot.projectcomics;

/**
 * Created by Kamelot on 05/01/2017.
 */

public class Serie {

    private String Totalepisodes;
    private String name;

    public String getTotalepisodes() {
        return Totalepisodes;
    }

    public void setTotalepisodes(String totalepisodes) {
        Totalepisodes = totalepisodes;
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
                "Episodios totales= '" + Totalepisodes + '\'' +
                ", Nombre= '" + name + '\'' +
                '}';
    }
}
