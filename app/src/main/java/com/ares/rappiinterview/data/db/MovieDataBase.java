package com.ares.rappiinterview.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ares.rappiinterview.data.model.Movie;

/**
 * Created by ares on 2019-05-28
 */
@Database(entities = {Movie.results.class}, version = 1, exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {

    private static MovieDataBase instance;

    public abstract  MovieDao movieDao();

    public static synchronized MovieDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDataBase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
