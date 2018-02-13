package com.sawaedaib.aibrahemsawaed.movielist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements MovieDataSource.OnMoviesArrivedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MovieDataSource.getMovies(this);

    }

    @Override
    public void onMovieArrived(@Nullable final ArrayList<Movie> movies, @Nullable final Exception e) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //code that runs on the UI (Main) Thread...!
                if (movies != null)
                    updateUI(movies);
                else if (e != null){
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    //TODO: present a dialog to the user... no internet
                }
            }
        });
    }

    private void updateUI(ArrayList<Movie> movies){

        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        MoviesAdapter adapter = new MoviesAdapter(movies,this);

        rvMovies.setAdapter(adapter);

        rvMovies.setLayoutManager(new LinearLayoutManager(null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
