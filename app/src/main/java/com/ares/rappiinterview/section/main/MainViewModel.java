package com.ares.rappiinterview.section.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ares.rappiinterview.RappiApp;
import com.ares.rappiinterview.data.Repository;
import com.ares.rappiinterview.data.model.Movie;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ares on 2019-05-28
 */
public class MainViewModel extends AndroidViewModel implements Repository.ClientCallBack.moviesCallback {

    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private RappiApp rappiApp;
    private LiveData<List<Movie.results>> getAllMovies;


    public MainViewModel(@NonNull Application application) {
        super(application);
        rappiApp = (RappiApp) application;
        getAllMovies = rappiApp.component().dbRepository().getAllMovies();
    }

    public LiveData<Movie> getPopularMovies(int page, String language) {
        getMovies(page, language);
        return movie;
    }

    public LiveData<Movie> getTopRated(int page, String language) {
        getTopMovies(page, language);
        return movie;
    }

    public LiveData<Movie> getUpComing(int page, String language){
        getUpComingMovies(page, language);
        return movie;
    }

    public void dispose(){
        disposable.clear();
    }

    private void getMovies(int page, String language) {
        disposable.add(rappiApp.component().repository().getPopularMovies(this, page, language));
    }

    private void getTopMovies(int page, String language) {
        disposable.add(rappiApp.component().repository().getTopRatedMovies(this, page, language));
    }

    private void getUpComingMovies(int page, String language) {
        disposable.add(rappiApp.component().repository().getUpComingMovies(this, page, language));
    }

    public void insert(Movie.results movie){
        rappiApp.component().dbRepository().insert(movie);
    }

    public LiveData<List<Movie.results>> getAllMovies() {
        return getAllMovies;
    }

    @Override
    public void onSucceed(Movie movie) {
        this.movie.setValue(movie);
    }

    @Override
    public void onError(Throwable error) {
        Log.wtf("onError", error.toString());
    }
}
