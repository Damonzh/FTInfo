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


    /**
     * 获取流行影片列表，数据每天更新一次
     * @param apiKey
     * @param page
     * @param language
     * @param callBack
     */
    @GET("/movie/popular")
    void getPopularMovies(@Query(APIConstant.KEY_API_KEY) String apiKey,
                          @Query(APIConstant.KEY_PAGE) int page,//页码  1--1000
                          @Query(APIConstant.KEY_LANGUAGE) String language,
                          Callback<MoviesWrapper> callBack);

    /**
     * 获取高评分的影片列表，数据每天更新一次
     * @param apiKey
     * @param page
     * @param callBack
     */
    @GET("/movie/top_rated")
    void getTopRatedMovies(@Query(APIConstant.KEY_API_KEY) String apiKey,
                           @Query(APIConstant.KEY_PAGE) int page,//页码  1--1000
                           Callback<MoviesWrapper> callBack);


    /**
     * 获取最新影片列表
     * @param apiKey
     * @param callback
     */
    @GET("/movie/latest")
    void getLatestMovies(@Query(APIConstant.KEY_API_KEY) String apiKey,
                         Callback<MoviesWrapper> callback);


    /**
     * 获取一周新片，数据每天更新一次
     * @param apiKey
     * @param page
     * @param language
     * @param callBack
     */
    @GET("/movie/now_playing")
    void getNowPlayingMovies(@Query(APIConstant.KEY_API_KEY) String apiKey,
                             @Query(APIConstant.KEY_PAGE) int page,//页码  1--1000
                             @Query(APIConstant.KEY_LANGUAGE) String language,
                             Callback<MoviesWrapper> callBack);

    /**
     * 获取即将上映影片信息，每天更新
     * @param apiKey
     * @param page
     * @param language
     * @param callBack
     */
    @GET("/movie/upcoming")
    void getUpcomintMovies(@Query(APIConstant.KEY_API_KEY) String apiKey,
                           @Query(APIConstant.KEY_PAGE) int page,//页码  1--1000
                           @Query(APIConstant.KEY_LANGUAGE) String language,
                           Callback<MoviesWrapper> callBack);
}
