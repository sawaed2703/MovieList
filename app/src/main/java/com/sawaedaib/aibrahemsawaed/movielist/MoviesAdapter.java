package com.sawaedaib.aibrahemsawaed.movielist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aibrahemsawaed on 13/02/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {



    private ArrayList<Movie> movies;
    private Context context;
    private LayoutInflater inflater;


    public MoviesAdapter(ArrayList<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.movie_item, parent, false);

        return new MoviesViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, int position) {
        final Movie movie = movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(String.valueOf(movie.getRelaeaseYear()));


        String url = movie.getImage();

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Movie: " + movie.getTitle()
                        +"Year"+movie.getRelaeaseYear()
                        +"Rating" + movie.getRating(), Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    builder = new AlertDialog.Builder(context,android.R.style.Theme_Material_Dialog);

                }else {builder = new AlertDialog.Builder(context);}



                builder.setTitle("Moview Info").setMessage("Title :"
                +"\n"+"ReleaseYear:"+ movie.getRelaeaseYear()
                +"\n"+"Rating:"+ movie.getRating()
                ).setPositiveButton(R.string.Close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                        .setIcon(R.mipmap.ic_launcher_round).show();




            }
        });

        Picasso.with(context).
                load(url).
                placeholder(R.drawable.ic_placeholder).
                error(R.drawable.ic_error).
                into(holder.ivPoster);
    }





    @Override
    public int getItemCount() {

        return movies.size();
    }

    public static class MoviesViewHolder extends RecyclerView.ViewHolder {
        //no encapsulation for efficiency:
        TextView tvTitle, tvYear;
        ImageView ivPoster;
        View v;

        public MoviesViewHolder(View v) {
            super(v);
            this.v = v;
            ivPoster = v.findViewById(R.id.ivPoster);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvYear = v.findViewById(R.id.tvYear);
        }
    }

}
