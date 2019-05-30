package com.ares.rappiinterview.section.details;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ares.rappiinterview.RappiApp;
import com.ares.rappiinterview.data.Repository;
import com.ares.rappiinterview.data.model.Details;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ares on 2019-05-28
 */
public class DetailsViewModel extends AndroidViewModel implements Repository.ClientCallBack.detailsCallback {

    private MutableLiveData<Details> details;
    private CompositeDisposable disposable = new CompositeDisposable();
    private RappiApp rappiApp;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        rappiApp = (RappiApp) application;
    }

    public LiveData<Details> getMovieDetails(int movie_id, String language){
        if(details == null){
            details = new MutableLiveData<>();
            getDetails(movie_id, language);
        }
        return details;
    }

    private void getDetails(int movie_id, String language) {
        disposable.add(rappiApp.component().repository().getDetails(this, movie_id, language));
    }

    public void dispose(){
        disposable.clear();
    }
    @Override
    public void onSucceed(Details details) {
        this.details.setValue(details);
    }

    @Override
    public void onError(Throwable error) {
        Log.wtf("onError", error.toString());
    }
}
