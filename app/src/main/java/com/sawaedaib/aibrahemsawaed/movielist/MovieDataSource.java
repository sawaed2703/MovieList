package com.sawaedaib.aibrahemsawaed.movielist;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by aibrahemsawaed on 13/02/2018.
 */

public class MovieDataSource {

    public interface OnMoviesArrivedListener{
        void onMovieArrived(@Nullable ArrayList<Movie> movies,@Nullable Exception e);
    }

    public static final String adress = "https://api.androidhive.info/json/movies.json";

    public static void getMovies(final OnMoviesArrivedListener listener){
        //Manifest permission Internet

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<Movie> movies = getMoviesSync();
                    listener.onMovieArrived(movies,null);
                }catch (Exception e){
                    listener.onMovieArrived(null,e);
                }
            }
        });

        thread.start();
    }

    private static ArrayList<Movie>getMoviesSync() throws IOException, JSONException{
        ArrayList<Movie>movies = new ArrayList<>();
        URL url = new URL(adress);

        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        InputStream in = con.getInputStream();

        String json = read(in);

        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject movieObject = jsonArray.getJSONObject(i);

            String title = movieObject.getString("title");
            int releaseYear = movieObject.getInt("releaseYear");
            double rating = movieObject.getDouble("rating");
            String image = movieObject.getString("image");

            JSONArray genreArray = movieObject.getJSONArray("genre");
            String [] genres = new String[genreArray.length()];

            for (int j = 0; j <genreArray.length() ; j++) {
                String g = genreArray.getString(j);
                genres[j]= g;
            }
            movies.add(new Movie(title,image,releaseYear,rating,genres));

        }
        return movies;

    }
    private static String read(InputStream in)throws IOException{
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line = null;

        while ((line = reader.readLine())!=null)
            builder.append(line);

            return builder.toString();

    }


}
