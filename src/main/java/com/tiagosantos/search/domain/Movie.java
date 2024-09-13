package com.tiagosantos.search.domain;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private List<Cast> artists;
    private String pathFile;

    public Movie(String name, int year, List<Cast> artists, String pathFile)
    {
        this.name=name;
        this.year=year;
        this.artists=artists;
        this.pathFile=pathFile;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Cast> getArtists() {
        return artists;
    }

    public void setArtists(List<Cast> artists) {
        this.artists = artists;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", artists=" + artists.toString() +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }
}
