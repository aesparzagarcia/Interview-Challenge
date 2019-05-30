package com.ares.rappiinterview.data;

import com.ares.rappiinterview.BuildConfig;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ares on 2019-05-25
 */
public class DataRepository implements Repository {

    private RestApiService repository;

    @Inject
    public DataRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        repository = retrofit.create(RestApiService.class);
    }

    @Override
    public Disposable getPopularMovies(ClientCallBack.moviesCallback callback, int page, String language) {
        return repository.getPopularMoviews(page, language, BuildConfig.PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSucceed,
                           callback::onError);
    }

    @Override
    public Disposable getTopRatedMovies(ClientCallBack.moviesCallback callback, int page, String language) {
        return repository.getTopRated(page, language, BuildConfig.PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSucceed,
                           callback::onError);
    }

    @Override
    public Disposable getUpComingMovies(ClientCallBack.moviesCallback callback, int page, String language) {
        return repository.getUpComing(page, language, BuildConfig.PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSucceed,
                           callback::onError);
    }

    @Override
    public Disposable getDetails(ClientCallBack.detailsCallback callback, int movie_id, String language) {
        return repository.getDetails(movie_id, language, BuildConfig.PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback::onSucceed,
                           callback::onError
                );
    }
}
