package com.ares.rappiinterview.data;

import com.ares.rappiinterview.data.model.Details;
import com.ares.rappiinterview.data.model.Movie;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ares on 2019-05-25
 */
public interface RestApiService {

    /***
     * API end points
     */
    @GET("movie/popular?")
    Observable<Movie> getPopularMoviews(@Query("page") int page,
                                        @Query("language") String language,
                                        @Query("api_key") String apiKey);

    @GET("movie/top_rated?")
    Observable<Movie> getTopRated(@Query("page") int page,
                                          @Query("language") String language,
                                          @Query("api_key") String apiKey);

    @GET("movie/upcoming?")
    Observable<Movie> getUpComing(@Query("page") int page,
                                  @Query("language") String language,
                                  @Query("api_key") String apiKey);

    @GET("movie/{movie_id}?")
    Observable<Details> getDetails(@Path("movie_id") int id,
                                   @Query("language") String language,
                                   @Query("api_key") String apiKey);


}
