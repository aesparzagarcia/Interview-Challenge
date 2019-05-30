package com.ares.rappiinterview.data.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ares.rappiinterview.data.model.Movie;

import java.util.List;

/**
 * Created by ares on 2019-05-28
 */
public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<Movie.results>> allMovies;

    public MovieRepository(Application application) {
        MovieDataBase movieDataBase = MovieDataBase.getInstance(application);

        movieDao = movieDataBase.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public void insert(Movie.results movie){
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public LiveData<List<Movie.results>> getAllMovies(){
        return allMovies;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie.results, Void, Void>{

        private MovieDao movieDao;

        public InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }


        @Override
        protected Void doInBackground(Movie.results... results) {
            movieDao.insert(results[0]);
            return null;
        }
    }

}
