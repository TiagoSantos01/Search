package com.tiagosantos.search.domain;

import java.util.List;

public class Movie {
    private String name;
    private int year;
    private List<Cast> casts;
    private String pathFile;

    public Movie(String name, int year, List<Cast> casts, String pathFile)
    {
        this.name=name;
        this.year=year;
        this.casts =casts;
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

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
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
                ", casts=" + casts.toString() +
                ", pathFile='" + pathFile + '\'' +
                '}';
    }
}
