package com.ares.rappiinterview.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ares.rappiinterview.data.model.Movie;

import java.util.List;

/**
 * Created by ares on 2019-05-28
 */
@Dao
public interface MovieDao {

    @Insert
    void insert(Movie.results movie);

    @Query("SELECT * FROM movie_table")
    LiveData<List<Movie.results>> getAllMovies();
}
