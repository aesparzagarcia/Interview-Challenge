package com.ares.rappiinterview.data;

import com.ares.rappiinterview.data.model.Details;
import com.ares.rappiinterview.data.model.Movie;

import io.reactivex.disposables.Disposable;

/**
 * Created by ares on 2019-05-25
 */
public interface Repository {

    Disposable getPopularMovies(ClientCallBack.moviesCallback callback, int page, String language);

    Disposable getTopRatedMovies(ClientCallBack.moviesCallback callback, int page, String language);

    Disposable getUpComingMovies(ClientCallBack.moviesCallback callback, int page, String language);

    Disposable getDetails(ClientCallBack.detailsCallback callback, int page, String language);


    public interface ClientCallBack {
        interface moviesCallback {
            void onSucceed(Movie movie);

            void onError(Throwable error);
        }

        interface detailsCallback {
            void onSucceed(Details details);

            void onError(Throwable error);
        }

    }
}
