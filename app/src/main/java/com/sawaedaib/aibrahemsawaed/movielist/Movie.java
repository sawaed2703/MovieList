package com.sawaedaib.aibrahemsawaed.movielist;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aibrahemsawaed on 13/02/2018.
 */

public class Movie {
    private final String title;
    private final String image;
    private double rating;
    private final int relaeaseYear;
    private ArrayList<String> genres = new ArrayList<>();

    public Movie(String title, String image, int relaeaseYear, double rating , String...genre) {
        this.title = title;
        this.image = image;
        this.rating = rating;
        this.relaeaseYear = relaeaseYear;

        if (genre!=null){
            Collections.addAll(genres,genre);
        }
    }



    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getRelaeaseYear() {
        return relaeaseYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", rating=" + rating +
                ", relaeaseYear=" + relaeaseYear +
                ", genres=" + genres +
                '}';
    }
}
