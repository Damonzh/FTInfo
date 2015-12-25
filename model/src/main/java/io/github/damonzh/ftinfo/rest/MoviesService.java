package io.github.damonzh.ftinfo.rest;

import io.github.damonzh.ftinfo.bean.MoviesWrapper;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public interface MoviesService {

    @GET("/movie/popular")
    void getPopularMovies(@Query("api_key") String apiKey,
                          @Query("page") int page,//页码  1--1000
                          @Query("language") String language,
                          Callback<MoviesWrapper> callBack);
}
